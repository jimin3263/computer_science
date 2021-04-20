# Protocol Stack

## Internet에서의 Protocol 역할
- Interner: SW/HW 복합적으로 이루어짐
- 기능 관점에서는 protocol들의 집합
- 삼성: 철수, 영희로 이루어져있다 -> X / 개발팀, 인사팀으로 이루어져있다 -> O : 기능 관점 !  

## Protocol  
- Internet 안에서의 각 entity (router, end system)들이 동작하는 방식  
- protocol : software 일수도 있음
- 상호작용을 하면서 동작을 한다  
- format, 메시지 주고 받음  
- 여러 개의 protoco이 서로 얽혀서 동작
- 위 아래 protocol끼리 API로 상호 작용
- 상대방에게 똑같은 protocol이 있다 (대칭적) -> 상호작용


### Protocol Model - Service  
- Client 에게 무언가를 해주는 것
- Communication service model  
- SAP(service access point)를 통해 packet 전달 서비스 제공 / sap으로 service와 protocol이 소통함  
- SP(Service Primitive)를 통해 서비스 받음 / service 와 protocol이 공통적인 언어가 있어서 소통을 함 -> 공통된 언어: SP(메시지 형태)
  - service -> protocol 요청, protocol -> service 보고 할때 SP 형식으로 주고 받음  
  - 이름, 형식, 파라미터로 구성됨
  - 형식 (primitive 종류)
    - request: 서비스 시작 요청
    - indication: protocol에서 service시작
    - response: 회신
    - confirm: 서비스 응답

### Protocol Model - Entity and SAP 
- Entities (SAP에서 내려오는 걸 처리): service provider 내에서 서로 interacting하면서 실제 동작을 하는 존재  
- Peer entity: 반대편에 대응됨
- communication service 실현을 위해 서로 message 주고 받음
- 하나의 SAP은 하나의 entity에 mapping, 여러 개의 SAP에 하나의 entity는 가능

### communication protocol  
-> peer entity들 간 상호 작용을 하는 규칙
- Service provider 내에서 service를 실현하기 위한 도구/ 규칙
- Syntax(protocol이 내보내는 PDU의 형식),Semantics(Syntax에 정의된 값들이 어떤 내용을 하는지, 어떤 동작을 하는지),timing(언제 보내고, 언제 동작) 
 
### Protocol Model - Protocol Representation  
Time sequence diagram: 세로축 - 시간흐름, 가로축 - 한쪽 entity -> peer entity 상호 작용  

-> SAP을 통해서 CR 들어옴  
 
### Protocol Model - PDU(Protocol Data Units)
- PDU: peer entity간 서로 주고 받는 message
- PDU의 포맷은 protocol에 의해 정의됨
  - peer entity 양쪽에 format이 알려져 있고 한 PDU에 대해 양쪽에서 동일하게 해석
- SDU (service가 primitive를 통해 내려주는 원래 data)  

### Principle of Transparency   
- (서비스가 내려준)SDU를 조작없이 그대로 통과(내용 변하면 안됨)
- SDU 내용을 참고하여 동작하지 않음
- PCI -> Protocol header/trailer (제어정보)  
  - 송신 측 entity에서 붙이고 수신 측 entity에서 제거

### Protocol function 
- error control : 정상적으로 전달되지 않은 상황
- flow control, fragmentation: 속도, 형태 조절

### Protocol 동작의 특성
- Concurrency: 일련의 이벤트가 동시에 일어나더라도 커버가능 해야함 -> 어떤 상황에서도 예외없이 돌아가야 함
- Nondeterminism: 이벤트가 어떤 순서로 발생하더라도 잘 작동되어야함 -> 모든 시나리오에 대처할 수 있어야함

### Layers
- 하나의 layer: SAP+entity
- SAP을 통해서 primitive 주고 받음, 위에서 아래로 소통
- service: application일수도, 바로 위인 layer, protocol 일수도
- parameter: 소통에 관련된 정보
- primitive: layer 소통
- PDU: peer entity끼리 소통

### Layered Architecture
- Protocol stack도 결국 layered architecture의 한 사례
- 티켓팅, 수화물, 게이트 통과 --> 스택구조 (대칭을 이룸)  
- horizontal interaction: peer entities끼리만 interaction, layer 간 interacion은 없음
- 상위, 하위에서 서로 영향 주지 않음
- Modularity
  - 기능 단위로 시스템 분해 -> 각 사람이 기능을 하나씩, 역할 분담 쉬워짐
  - 일부 기능에 대한 수정이 용이
- 기능의 series 형태로 표현, 동작 흐름이 한눈에 보임
  - 동작 분석, 디버깅, 시스템 동작을 용이하게 볼 수 있음
- principle of transparency: SDU 그대로 통과, protocol 동작이 SUD에 의해 영향 받지 않음

### PDU 관점에서 Layered Architecture
- 아래로 갈수록 커짐
- fragmentation이 일어날 수 있음 -> 상위 레이어 입장에서는 SDU를 잘 받을 수 있게 하위 레이어에서 잘 보내고 다시 잘 붙이도록 해야함

### OSI
- ISO에서 제안한 통신 네트워크의 표준 계층 구조  
- 7계층 구조/ 5계층 구조  
- L1, L2 -> 1hop 하나의 장치에서 다른 장치로 한번 이동할 때  
- L1: Physical layer(link layer) -> 물리적으로 연결된 링크에 맞는 실제 물리 신호 생성
  - 1:1 통신할때 물리적인 매체를 이용해서 통신을 해야함
  - Wifi -> 무선에 맞는..  
  - 어떻게 물리적인 신호를 만드는가?  
  - 전송률이 가장 큰 성능 지표  
- L2 (MAC layer): layer1 제어 
  - 여러 개체가 하나의 통신 매체를 공유하는 것에 대한 교통 정리
- L3 (IP) -> 우체통에 넣으면 우체통에 전달할 때까지의 역할
- L4 : end to end로 정보 전달할 때 -> 흐름조절 (오늘 편지가 많으면 내일 전달해야지 ~ )  
- Application layer: HTTP, SMTP, FTP, DNS(IP address <-> 이름)  
<br>

### 과정
host A -> host B   
1. host A의 application이 정보 만들어서 TCP 쪽의 SAP에다가 정보 보냄 (SDU)  
2. TCP -> IP (PDU) : host B의 주소 받음  
3. L2,L1 에 PDU 보내고 링크를 통해서 전달  
4. 네트워크 타고해서 중간 router에 전달   
5. router에서 host B로 전달  
6. 아래서 위로 -> application으로 SAP 전달  

### Encapsulation 
- layer를 통과하면서 PDU가 되면서 점점 header가 붙는다  

### QUIZ

- 하나의 레이어에서 상위레이어에서 받은 SDU에 Protocol Control Information을 결합하여 이것을 생성하는데 peer entity 간의 서로 주고받는 메시지인 이것을 무엇이라 하는가?  
-> PDU  
- 서비스와 SAP 간, 혹은 상위 레이어와 현재 레이어가 주고받는 메시지를 무엇이라 하는가? 이것의 형태는 request / indication / response / confirm 이 있다.  
-> primitive  

