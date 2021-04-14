# Protocol Functions

## Error control
- PDU가 정상적으로 전달되지 않은 상황에서 protocol entity의 동작
- fragmentation(자르거나), flow control(흐름 조절)
- Connection-oriented communications protocol: 연결 기반의 프로토콜  
- PDU전달 과정에 문제 생김 -> 핸들링해주는 과정  

```
Detect: 송신 측에서 error 상황 인지  
-> PDU를 보내고 수신이 제대로 되었는지 ?  
React: error 상황에서 송신 측 동작  
-> 송신측에 error 발생 알아내고 대처  
```

### Detect
- Confirmation
: 수신측에서 알려줌
![image](https://user-images.githubusercontent.com/50178026/114420017-e74c6f80-9bee-11eb-8c66-0036ccc7ae9b.png)  

- Explicit(외부적인 요인으로 ACK)
  - Positive ACK: 정상 수신된 PDU를 알려주는 방식 (sequence number) / Cumulative ACK: 다 받은 후 보냄 
  - Negative ACK: 미수신 ACK알려줌 

### Timer
- ACK도 깨질 수가 있음
- TX 는 ACk을 기다리게 됨 -> Deadlock 발생
- timer 동반함 -> 보내는 쪽이 timer 시작, 시간내에 도달하지 않으면 깨졌다고 판단  
- timer interval = 데이터 보냄 + ACK받을 때까지 갔다오는 시간 + margin  
- time-out -> timer expiry  
- peer partner의 inactivity 감지  

<br>

#### timer의 중요성
- protocol 표준 문서 -> 타이머는 항상 있음 !
- timer name/ interval/ start/ stop/ expiry  
- timer interval: 적절한 길이 
- 너무 짧으면 리액션이 잦아지는 불필요한 상황 발생, 너무 길면 리액션이 너무 늦음  

#### Error control 의 시나리오
1. PDU Loss
- 데이터 보내는 동안 중간에 손실 발생  
2. Duplicaiton  
- 중복 발생 
- ACK 손실, TX 측에서 time-out으로 PDU wowjsthd
- 수신측에서 두 개면 하나 무시해도 됨  
-> sequence number로 감지 가능  

## ARQ (protocol)  
: Automatic Repeat reQuest  
ACK + timer + reactions  

> stop and wait   
- 가장 기본적인 ARQ (이전에 했던 방식)
- 하나를 보내고 ACK 기다림 
- 5G/ LTE 쓰이기도 함  

> go back N  
- n번째 error 발생 -> n부터 다시 재전송
- duplicatin 발생하기도 함   
- TX 는 ACK받기 전까지 PDU 저장하고 있어야 함  
- RX N번째 이후는 버림  
-> 버퍼 낭비가 큼  

> selective repeat  
- 손실된 PDU에 대해서만 골라서 재전송  
- RX는 제대로 받은 PDU 저장  

||Go back N|Selective repeat|
|------|---|---|
|buffer|TX|RX|
|시간|재전송 수신까지 시간 걸림|시간 덜 걸림|    

## FEC = Channel coding (protocol function)  
: Forward Error Control (자기치유 능력) 
- error correction  
- 스스로 오류 정정  
- bit를 뻥튀기해서 보냄 -> 수학계산으로 에러 정정  
- 계산량 많음, 실시간 방송 (재전송 안되는 상황)  

## CRC  
: Cyclic Redundant Check  
- FEC가 오류를 다 고쳐주진 못함
- 적어도 원본하고는 다르구나 -> error detection  
- 부가 정보를 붙이고 수신 측에서 부가 정보를 활용해서 깨졌는지 확인 (FEC만큼 두배,세배 늘리는 건 아님)  
- (+) parity bit(16/24 bit)  
- 수신 CRC, 내가 만든 CRC 비교
- FEC가 완벽하게 복원했는지 확인하는 용도  
- 미리 상호간에 약속된 다항식으로 나누어 나머지를 Parity bit