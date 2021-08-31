import http from "k6/http";
import { check, sleep } from "k6";

export let options = {
  thresholds: {
    // p(90)=1.62s  p(95)=1.84s
    http_req_duration: ['p(95) < 1000', 'p(99) < 1500'],
  },
//  vus: 1,
//  iterations: 3
  stages: [
    { duration: "2m", target: 100 }, // normal load
    { duration: "2m", target: 200 },
    // { duration: "2m", target: 300 }, // around the breaking point
    // { duration: "5m", target: 300 },
    // { duration: "2m", target: 400 }, // beyond the breaking point
    // { duration: "5m", target: 400 },
    // { duration: "10m", target: 0 }, // scale down. Recovery stage.
  ],
};

const BASE_URL = "http://localhost:8080" // "https://qa-spring-exams-api.herokuapp.com";
const eventExamId = 1;

function range(startAt = 0, size, ) {
    return [...Array(size).keys()].map(i => i + startAt);
}

export function setup() {
  const postulants = range(1, 1000).map(i => ({ 
            'username': `1001000${i}`, 
            'code': `1000${i}`
        }))
     
  return { postulants };
}

export default function (data) {
  const postulant = randomElement(data.postulants)
//	const postulant = data.postulants[0]
//	console.log(JSON.stringify(postulant))

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
  
  sleep(3)
  
  //CHECK EVENT IS DATE UPDATED
  const eventResponse = http.post(
    `${BASE_URL}/postulant-exam/start-or-get/${eventExamId}`,
    JSON.stringify({}),
    params
  );
  const { id: idPostulantExam } = eventResponse.json();

  for (let id = 1; id <= numberOfQuestions; id++) {
    // console.log(`${BASE_URL}/postulant-question/${idPostulantExam}/${id}`);
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
    
    // console.log(`Answer for Question ${id} is ${answerToMarkId}`);
    const markAnswerResponse = http.put(
      `${BASE_URL}/postulant-question/${idPostulantExam}/${id}/answer`,
      JSON.stringify({ alternativeId: answerToMarkId }),
      params
    );

    check(markAnswerResponse, {
      "mark answer is 200": r => r.status == 200
    });

  }

  sleep(1)
}

const randomElement = (array) => array[Math.floor(Math.random() * array.length)];