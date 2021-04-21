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
- Explicit(외부적인 요인으로 ACK)
  - Positive ACK: 정상 수신된 PDU를 알려주는 방식 (sequence number) / Cumulative ACK: 다 받은 후 보냄 
  - Negative ACK
    - faulty or outstanding sequence: 미수신 ACK 알려줌
    - active error control: 못받았으므로 골라서 받겠다 
  - piggybacking
    - 양방향일 때 Rx -> Tx PDU에 실어서 같이 ACK 정보 전송
- Implicit
  - 송신에서 ACK 받았다고 간주
- reaction: 재전송, 연결 끊음

### Timer
- ACK도 깨질 수가 있음
- TX 는 ACk을 기다리게 됨 -> Deadlock 발생
- timer 동반함 -> 보내는 쪽이 timer 시작, 시간내에 도달하지 않으면 깨졌다고 판단  
- timer interval = 데이터 보냄 + ACK받을 때까지 갔다오는 시간 + margin  
- time-out -> timer expiry -> plan B 실행
- ACK 도착하면 timer reset함
- Activity Timer
  - peer partner의 inactivity 감지  
- timer의 중요성
  - protocol 표준 문서 -> 타이머는 항상 있음 !
  - timer name/ interval/ start/ stop/ expiry  
  - timer interval: 적절한 길이 
  - 너무 짧으면 리액션이 잦아지는 불필요한 상황 발생, 너무 길면 리액션이 너무 늦어져 deadlock 구간 발생

#### Error control 의 시나리오
1. PDU Loss
- 데이터 보내는 동안 중간에 손실 발생 
2. Duplicaiton  
- 중복 발생 
- ACK 손실, TX 측에서 time-out으로 PDU 재전송
- 수신측에서 두 개면 하나 무시해도 됨    
*-> sequence number로 감지 가능*  
```
- RX 
  - time-out-interval 이후에도 도달하지 않는 경우
  - 더 큰 sequence number PDU 수신
- TX
  - ACK을 통해 빠진 sequence 인식
  - 중복된 ACK sequence 수신 
```

## ARQ (protocol)  
: Automatic Repeat reQuest 
: ACK + timer + reactions    
*-> 세가지 동작 포함, 여러 protocol function의 집합, 사실상 protocol*
> stop and wait   
- 가장 기본적인 ARQ (이전에 했던 방식)
- 하나를 보내고 ACK 기다림 
- 5G/ LTE 일부 쓰이기도 함  

> go back N  
- n번째 error 발생 -> n부터 다시 재전송
- duplication 발생하기도 함   
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
- 계산량 많음
- 실시간 방송 (재전송 안되는 상황)  
  - 시간이 지나면 PDU의 의미가 없어지는 서비스
- 오늘날 모든 무선 통신 시스템에서 활용됨

## CRC  
: Cyclic Redundant Check  
- FEC가 오류를 다 고쳐주진 못함
- 적어도 원본하고는 다르구나 -> error detection  
- 부가 정보를 붙이고 수신 측에서 부가 정보를 활용해서 깨졌는지 확인 (FEC만큼 두배, 세배 늘리는 건 아님)  
- (+) parity bit(16/24 bit)  
- 수신 CRC, 내가 만든 CRC 비교
- 주로 FEC가 완벽하게 복원했는지 확인하는 용도  
- 미리 상호간에 약속된 다항식으로 나누어 나머지를 Parity bit


### Synchronization  
- 영상에서의 싱크와 비슷  
- entity간 consistent한 protocol operation을 위해 서로의 state 맞추는 event 동작이 필요함  
- peer entity 간 connection (전화검: request, 받음: confirm -> 호가 맺어졌다)   
*-> connection 맺었다고 생각하고 data를 보내는데, RX에서 연결을 몰라서 discard 할 수도 있음 (받을 준비가 되어 있어야 한다)*  
*-> connection setup/ release에서 주로 활용됨*   
  
> 2-way handShaking   
: connection request -> confirm : 두번만에 synchronization  
- initiator(TX): request  <->  responder(RX): confirm 
- request: 요구하는 전송률, 지연, LTE, wifi -> 어떤 채널로 언제 어떤 간격으로 PDU의 크기 등 (파라미터는 종류에 따라 다름)   
- confirm: 받을 준비가 되었다 -> connection이 맺어졌다고 가정 


> 3-way handShaking   
: 2-way + TX가 complete 한 번 더  
- request - setup - complete  
![image](https://user-images.githubusercontent.com/50178026/114431119-17e5d680-9bfa-11eb-9e2b-93e899480cae.png)
- 이전에 request 받았기때문에 complete가 깨져도 연결 가정할 수 있음  
- 이후에 data PDU받으면 complete 보냈지만 못받았다고 간주하고 연결할 수 있게 됨  

### Connection Management
: Connection 설정/ 관리/ 해제  

> Connection establishment: 연결 맺음   
  - 연결 수용/ 거부 상태를 맞추는 과정   
  - TX가 이런 전송률 제한, 지연제한을 가지고 전송하겠다 ! -> RX가 거절할 수도   
  - QOS(Quality of service) -> throughput(전송률, 데이터 보낼때) / dalay (보이스톡, 패킷 간 거리 유지) / loss rate   
  - reject(거절), reduce(역제한), accept(받아들임)   
  - explicit: 명시적으로 설정 -> 메시지 교환을 마치고 서로 연결 상태로 전환  
  - setup : 2,3 way로 -> data transfer phase로 넘어감
  - implicit: 시작과 동시에 connection 맺어졌다고 가정 -> 손실될 수도 있어서 지양됨 (무선의 경우)  
  
> Connection Maintenance   
  - 하위 layer에서 문제 발생 -> 상위에게 알림    
  - connection이 끊어졌다 -> 다시 연결   
  - re-synchronization: 깨진 connection에 대해서 re-establishment    
  - reassignment: 새로운 connection  

> data transfer: 맺어진 상태에서 data 전송 관리  

> Connection release: connection 해제 
  - explicit release (데이터 다 전달한 후에 release) -> 전화하고 있다가 끊음/ 합의하에 종료필요(data 가고 있는데 release가 먼저 도착할 수도)   
  - abrupt release -> 비정상 종료(사람 너무 많은 곳에서 채널 공유)    
    - data transfer loss 필연적으로 발생
  - half-open connection problem: (d) RX는 release 됐는지 모름   
![image](https://user-images.githubusercontent.com/50178026/114437492-95611500-9c01-11eb-83f8-745c84c8c6c0.png)   

(c): 계속해서 release 보냄 (d): 계속 time-out -> 알아서 release    

#### Unidirectional connection에서의 release    
- TX 에서 release 요청해야 함 
- release 요청 PDU 또한 sequence number 가져야 함 n번째 "몇번째가 release 메시지다" -> release가 먼저 도착할 수 있으므로
- RX에서의 confirm까지 해야 함    

#### Bidirectional connection에서의 release 
- E1 connection 끊어져도 E2는 보낼 수 있음
- 3-way handshake에서의 synchronization    
- Timer로 해결
- msg2 loss: 양쪽 모두 expiry   
- msg3 loss: 수신 쪽만 expiry   
- msg1 loss: TX - PDU timer expiry   
             RX - activity timer expiry   

#### Abrupt connection release
- 비정상 상황에서 곧바로 connection 해제
  - 재전송해도 ack안옴, 보안 이슈
- data transfer loss 필연적
- 문제가 있는 link 재전송 방지 -> 해당 연결 freezing

### PDU Adjustment  
> N -> N-1 layer mapping
- one to one
- Multiplexing: 상위 layer의 여러 SAP을 모아서 처리
  - scheduling: 어떤거부터 내릴지
  - flow control: 두 개를 한번에 보낼지, 얼마만큼 등
  - Assignment: 수신한 N-1 PDU를 N layer에 적절히 올림, 어디에다가 올릴지
  
- Splitting: 하위 layer의 여러 SAP으로 분배
  - scheduling: N-1 layer의 SAP으로 보낼지 결정
  - aggregation: 수신 측에서 (N-1) connection들로부터 N PDU 생성 (올릴때 몇개를 모아서 올릴지)  

> SDU에 대한 길이 조정
- fragmentation: SDU가 너무 크면 잘라서 여러 PDU 생성  
  - Segmentation(분리): SDU를 자르고 sequence number(PDU header에)를 매김  
  - Reassembling(재조립, RX 쪽에서): reorder하고 합침  
-> PCI control message가 붙어서 overhead 발생하긴 함 / error control 이나 sycronization에서는 잘게 쪼개는게 유리함  

- Concatenation: 작은 SDU를 하나로 합쳐서 보냄  
  - Chaining: 여러 SDU를 합침
  - Separating: RX에서 다시 분리

- Sequence Number : PDU header에 붙임   
  - PDU 전송상태 확인  
  - reordering/ duplication -> 문제해결  
  - fragmentation, concatenation / 456 이 하나다 -> 복호화 가능  
  - 서로 다른 PDU -> 동일한 sequence 피해야 함 -> 사용한 숫자 freezing: 다시 사용할 수 없음 -> forbidden zone 
  - sequecne number overflow (10~12 bit -> 너무 크면 overhead) : 최대값이 충분히 크도록, wrap around(mod 돌려쓰기)    
  
- Flow Control
  - PDU 개수 조정
  - 채널 용량
  - 언밸런스 현상을 위해
  - window-based flow control: first transmission PDU range 내에서만 전송/ retransmission은 제한 없이
  - start/stop procedure: 수신 측에서 stop/ start -> sidnal/ 잦은 stop signal -> overhead
  - credit procedure: 수신 측에서 credit을 줌/ sender는 허용된 credit에 따라 전송, 송신 측은 허용된 범위의 PDU를 보내고 나서 credit을 받을때까지 기다림
  - Sliding window protocol
    - 수신측에서 window 증감을 통해 credit을 줌
  - Rate-based flow control
    - 전송률에 대한 제한
    - window 크기 자체를 바꿈(유선) -> 제한된 데이터 크기에서 여러개의 SAP가 나눠서 쓰므로 한번 비면 여러개 보낼 수도
    - 무선은 둘다 사용
