## 통신 네트워크
: end node가 얽히고 섥힘

- end to end  
: 자동차(end) - 컴퓨터(end)  
end node끼리 통신이 이루어지도록 하는 기법  
자동차라는 end node가 어떤 통신망을 통해서 컴퓨터로 전달, 어떻게 전달, 전달하면서 발생하는 문제를 어떻게 해결  

- sub-networks  
: home network(wifi), mobile network(LTE) -> sub network가 모여서 큰 net work 형성 


## Network
: 프로토콜(서로간의 약속된 규약)의 집합  
- end node -> end node 데이터 전달과정에서 서로간의 약속된 규약  
- 연결 사이에 프로토콜이 동작함  


## 하드웨어 관점에서의 Internet
- TCP(Transmission Control Protocol)/IP(Internet Protocol) 라는 통신 프로토콜을 이용해 정보를 주고받는 컴퓨터 네트워크  
- TCP/IP 통신 프로토콜을 굳이 이용하지 않아도 컴퓨터 사이에서 주고 받는 네트워크 기반 -> 컴퓨터 사이에서 이루어지고 있는 네트워크  
- 오늘날 모든 네트워크 공학 기술을 포함한 존재  
- Data를 전달하는 장치들이 이루는 거대한 network망  
- 컴퓨터에서 동작하는 application(메일)이 다른 end node에게 망을 통해서 데이터를 전달하려고 할 때, 데이터를 전달하는 전체적인 네트워크  
- IOT/모바일 기기 또한 통신에 참여함 -> 인터넷에 접속되어있다 -> application to application 데이터 전달하기위해 네트워크 이용  
- arpanet -> world wide  

### Internet 구성
- sub network들로 구성   
- sub network: mobile network(LTE, 5G), home network, 학교 내부 망   

> network edges    

- 나무의 이파리 (끝에 있는 자동차, 노트북 -> 직접적으로 사용하고 있는 디바이스)  
- end system 으로 이루어져 있음  
- end system: client(phone, 노트북), server(웹 서버, 어플리케이션 서버, 대용량을 가진 컴퓨터)  
- application이 구동되는 존재  
- 웹서버에 접근: 브라우저라는 어플리케이션이 돌아가고, 웹 서버는 어플리케이션에서 request 받아서 reponse 보내줌  


> access networks    

- 기기들이 인터넷에 접속하기 위해서 사용하는 network  
- sub network의 기반이 됨
- network edge가 인터넷에 접근하기위해 가장 먼저 연결되는 구간  
- 3G, LTE, ethernet, 블루투스  
- 방식에 따라 전송률, 지연 등이 달라짐


> Network core     

- 도시간의 통신, 중심 도시로부터 국가간의 통신 -> 전 세계적인 망의 중점이 되는 척추   
- end system의 정보를 실어나르는 척추
- 기지국(SKT,KT,U+) -> 집중국사 -> 인터넷 연결
- packet switches(router, link-layer switches) 기반, 내가 원하는 곳으로 전달해줌  
- store and forward transmission : 각각의 코어에는 버퍼가 있어서 먼저 들어온 것들 먼저 빼줌
- 허브(ethernet 꽂을 수 있는) -> 패킷을 어디로 전달할 것인지  
- packet 단위 (host -> hose data 뭉치)  
- packet Switching network: packet 기반의 통신 네트워크 (잘게 자른 패킷단위로)  
  - store and forward transmisson
  - dlay time: 2*L/R
  - Congestion: 여러개의 입력 node와 연결된 packet switch에서 혼잡 현상
  - Queueing: output buffer에 packet이 정체되는 현상
  - packet loss: buffer에 저장 못하고 packet이 버려지는 현상  
  - router: 연결된 여러 출력 node에게 packet을 적절히 전달
  - forwarding table: packet 전달의 기본 원칙 - destination 으로 가기까지 어떤 link로 보낼 것 인지
  - routing protocol: 자동으로 forwarding table 생성하는 기법
- Circuit Switching network: 한번 연결되면 계속 유지됨  
  - Queueing, packet loss는 없음, capacity 떨어짐


## 서비스 관점에서 Internet  
- application에 서비스 제공 
- email, SNS, messenger  
- 다른 application에 가기 위해서 internet이 알아서 전달  
- API : internet으로 하여금 packet을 제대로 보내게 하기 위한 가이드

## Protocol
- Internert 내에서 각 entity들의 동작 그 자체
- entity들이 어떻게 다음 router에 전달하고 그 router가 edge router에 전달하고 edge router는 destination node 한테 어떤 access network를 통해서 전달할 것인지 ..?  
- 모든 entitiy 간 서로 약속된 것이어야 함 (표준)
- IETF에서 표준 만들어짐 -> TCP, IP, HTTP, SMTP  

## Internet Performance
- 성능 지표 
- Delay: end system 간 packet이 전달되는 시간  
- Throughput: 충분히 큰 파일을 전송하는 경우 수신 측에서 측정되는 전송률  

## Internet Trend
- PC -> mobile -> IoT    
- data sharing (cloud)  
- web browsing  
- mobile app  
- real-time service (보이스 톡)  
- Traffic 증가
- faster and cheaper
- more intelligent
- everything over IP (보안 이슈)  
- mobility (컴퓨터를 휴대폰에서 바로 가능 clouding)  

## Network 산업
- CISCO : packet switch/router 독점  
- LG + Notel + Ericsson : 기지국  
- Nokia + Alcatel Lucent + Siemens : 기지국  
- Samsung : LTE/5G     
- Huawei : 셀룰라 네트워크 


## QUIZ 
Data를 전달하는 장치들이 이루는 망으로, Network Edges/Access networks/Network Core로 이루어진 거대한 네트워크   
-> internet  
End system간에 정보를 전달하는 과정에서 만들어지는 data 뭉치이자 Network Core에서 정보를 전달하는 전송 기본 단위로서, 인터넷에 있는 모든 switch나 router는 이 단위로 다음 네트워크 기기에 전달하게 된다. 이것은 무엇인가?  
-> packet  
