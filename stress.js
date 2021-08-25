import http from "k6/http";
import { check, sleep } from "k6";

export let options = {
  vus: 2,
  duration: "10s"
  // stages: [
  //   // { duration: "1m", target: 1 }, // below normal load
  //   // { duration: "5m", target: 100 },
  //   // { duration: "2m", target: 200 }, // normal load
  //   // { duration: "5m", target: 200 },
  //   // { duration: "2m", target: 300 }, // around the breaking point
  //   // { duration: "5m", target: 300 },
  //   // { duration: "2m", target: 400 }, // beyond the breaking point
  //   // { duration: "5m", target: 400 },
  //   // { duration: "10m", target: 0 }, // scale down. Recovery stage.
  // ],
};

const BASE_URL = "https://prod-spring-exams-api.herokuapp.com";
const eventExamId = 1;


export function setup() {
  const loginAdminResponse = http.post(
    `${BASE_URL}/auth/login`,
    JSON.stringify({ name: "unmsm-admin", password: "unsuperpasword" }),
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  );

  const token = loginAdminResponse.body;

  const params = {
    headers: {
      Authorization: token,
      "Content-Type": "application/json",
    },
  };

  const responseResults = http.get(
    `${BASE_URL}/exam-results/${eventExamId}?size=100`,
    params
  );
  let postulants = responseResults.json().content.map(x => x.postulant)
  return { postulants };
}

export default function (data) {
  const postulant = randomElement(data.postulants)
  const numberOfQuestions = 25;

  const loginResponse = http.post(
    `${BASE_URL}/auth/login`,
    JSON.stringify({
      name: postulant.username,
      password: postulant.code,
    }),
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  const token = loginResponse.body;

  const params = {
    headers: {
      Authorization: token,
      "Content-Type": "application/json",
    },
  };
  
  const { principal } = http.get(`${BASE_URL}/auth/me`, params).json();
  
  //CHECK EVENT IS DATE UPDATED
  const eventResponse = http.post(
    `${BASE_URL}/postulant-exam/start-or-get/${eventExamId}`,
    JSON.stringify({}),
    params
  );
  const { id: idPostulantExam } = eventResponse.json();

  for (let id = 1; id <= numberOfQuestions; id++) {
    console.log(`${BASE_URL}/postulant-question/${idPostulantExam}/${id}`);
    const responseQuestion = http.get(
      `${BASE_URL}/postulant-question/${idPostulantExam}/${id}`,
      params
      );
    
    
    const { alternatives } = responseQuestion.json();
    
    check(responseQuestion, {
      "question is 200": () => responseQuestion.status == 200,
      "has alternatives": () => alternatives.length != 0,
    });

    
    let answerToMarkId = randomElement([...alternatives, {id: null}]).id
    
    console.log(`Answer for Question ${id} is ${answerToMarkId}`);
    const markAnswerResponse = http.put(
      `${BASE_URL}/postulant-question/${idPostulantExam}/${id}/answer`,
      JSON.stringify({ alternativeId: answerToMarkId }),
      params
    );

    check(markAnswerResponse, {
      "mark answer is 200": r => r.status == 200,
      "postulant answer is what is marked": r => r.json().postulantAnswerId == answerToMarkId,
    });

  }

  sleep(1)
}

const randomElement = (array) => array[Math.floor(Math.random() * array.length)];