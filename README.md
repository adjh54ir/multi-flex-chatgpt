# multi-flex-chatgpt

    ⭕️ Spring Boot 기반의 ChatGPT 활용 API

⭕️ 가이드 링크 : https://adjh54.tistory.com/372

<br/><br/>

## 1. 개발환경

![dacdc261-8c0c-4aa0-9377-718f2d9eda0a](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/dcd52b23-cea1-493e-b47f-4b32f20f39d1)


| 환경 분류                   | 버전      |
|-------------------------|---------|
| JDK                     | Java 17 |
| spring-boot             | 3.2.1   |
| spring-boot-starter-web | 3.2.1   |
| Lombok                  | -       |

<br><br>

## 2. 사용방법

1. src/main/resources/config 패키지가 없다면 구성하며, 해당위치에 application-xxx-local.yml 형태의 파일을 생성합니다.


2. 아래와 OpenAI에서 발급받은 키 값을 넣어줍니다.

 ```java
openai:
secret-key:xxxxx
```

3. application.properties 파일 내에 해당 yml 파일을 참조합니다.

 ```java
spring.profiles.active=multiflex-xxx
```   

<br><br>

## 3. API Endpoint

| End point                                          | 설명                                                                                   |
|----------------------------------------------------|--------------------------------------------------------------------------------------|
| http://localhost:8000//api/v1/chatGpt/modelList    | 사용 가능한 모델리스트를 조회합니다                                                                  |
| http://localhost:8000//api/v1/chatGpt/model        | 유효한 모델인지 확인합니다.                                                                      |
| http://localhost:8000//api/v1/chatGpt/legacyPrompt | 레거시 모델(gpt-3.5-turbo-instruct, babbage-002, davinci-002)을 사용하여 프롬프트를 입력하고 결과값을 받습니다. |
| http://localhost:8000//api/v1/chatGpt/prompt       | 새로운 모델(gpt-4, gpt-4 turbo, gpt-3.5-turbo)을 사용하여 프롬프트를 입력하여 결과값을 받습니다.                |


