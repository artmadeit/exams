import http from "k6/http";
import { check } from "k6";
import { sleep } from "k6";

export let options = {
  stages: [
    { duration: "2m", target: 100 }, // below normal load
    { duration: "5m", target: 100 },
    { duration: "2m", target: 200 }, // normal load
    { duration: "5m", target: 200 },
    { duration: "2m", target: 300 }, // around the breaking point
    { duration: "5m", target: 300 },
    { duration: "2m", target: 400 }, // beyond the breaking point
    { duration: "5m", target: 400 },
    { duration: "10m", target: 0 }, // scale down. Recovery stage.
  ],
};

export default function () {
  const BASE_URL = "https://spring-exams-api.herokuapp.com";

  const questionNumber = 25;
  const eventExamId = 1;

  const loginResponse = http.post(
    `${BASE_URL}/auth/login`,
    JSON.stringify({
      name: "73646447",
      password: "12123123",
    }),
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  const token = loginResponse.body;

  const options = {
    headers: {
      Authorization: token,
      "Content-Type": "application/json",
    },
  };
  const { principal } = http.get(`${BASE_URL}/auth/me`, options).json();
  const { dni } = principal;
  const eventResponse = http.post(
    `${BASE_URL}/postulant-exam/start-or-get/${eventExamId}`,
    JSON.stringify({}),
    options
  );
  const { id: idPostulantExam } = eventResponse.json();

  for (var id = 1; id <= questionNumber; id++) {

    const { alternatives } = http
      .get(`${BASE_URL}/postulant-question/${idPostulantExam}/${id}`, options)
      .json();
    const toMark = alternatives.find((a) => a.content == "alternativa B");
    console.log(`Answer for Question ${id} is ${toMark["id"]}`);

    const answer = { alternativeId: toMark["id"] };
    http.put(
      `${BASE_URL}/postulant-question/${idPostulantExam}/${id}/answer`,
      JSON.stringify(answer),
      options
    );
    const responseResult = http.get(`${BASE_URL}/exam-results/${eventExamId}?dni=${dni}`, options)
      
    check(responseResult, {
      "is score -25": (r) => {
        const { content } = r.json();
        var { exam } = content.find((c) => c.postulant.dni == dni);
        return exam.score == -25.0
      }
    });
  }

  sleep(1);
}
