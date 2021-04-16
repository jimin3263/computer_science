# Protocol Functions Ⅱ  

### Synchronization  
- 영상에서의 싱크와 비슷  
- entity간 consistent한 protocol operation을 위해 서로의 state 맞추는 event 동작이 필요함  

![image](https://user-images.githubusercontent.com/50178026/114429333-3cd94a00-9bf8-11eb-8563-2de888843fc3.png) 
-> connection 맺었다고 생각하고 data를 보내는데, RX에서 연결을 몰라서 discard 할 수도 있음 (받을 준비가 되어 있어야한다)  
-> connection setup/ release에서 주로 활용됨  
<br> 

- peer entity 간 connection (전화검: request, 받음: confirm -> 호가 맺어졌다)     
> 2-way handShaking   
: connection request -> confirm : 두번만에 synchronization  
- initiator(TX): request  <->  responder(RX): confirm 
- request: 요구하는 전송률, 지연, LTE, wifi -> 어떤 채널로 언제 어떤 간격으로 PDU의 크기 등 (파라미터는 종류에 따라 다름)   
- confirm: 받을 준비가 되었다 -> connection이 맺어졌다고 가정 
![image](https://user-images.githubusercontent.com/50178026/114431004-fa187180-9bf9-11eb-82c0-755645441037.png)

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
- TX 에서 release 요청해야 함 (ㄱ)
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
> N -> N-1
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
