# Layer3 - Internet Protocol  

- Layer2 - 전송 수행, error control  
- Layer1 - 물리적 전송  
  - hop 1개일 때 수행   
   
*-> 다음 hop까지 전달*

## Layer3
- link가 여러개 있을 때 source -> destination 어디로 데이터 전달할 것인지  
- 전달하고자 하는 패킷이 destination으로 향하는 경로를 찾는 역할  
- sub network 간 이동
- Router: packet forwarding을 주 역할로 하는 구성 요소 -> network 내에서 적잘한 방향으로 packet 전달
- Forwarding: input port -> out port (forwarding table 기반)
  - data plane: data만 주고 받는 경로/ 인터페이스 
  - local에서 action 일어남
- Routing: path를 파악하기 위한 제어 동작(경로 찾음) -> forwarding table 만듦  
- Forwarding table: header value -> output link
  - packt의 header 내 정보를 보고 forwarding table와 대조를 통해 어느 경로로 보낼 지 결정
- Router: L3관점(IP)에서 능동적인 역할
  - 중간에서 packet 전달  
  - forwarding, routing 만
  - IP 까지만 있음 (상위레이어 없음)

### Hub vs Router
- Hub (L1): physical layer 신호가 여과 없이 그대로 연결된 모든 end-node
  - 신호 증폭기(noise가 낄 수 있으니까)  
  - 데이터가 왔으니까 그대로 전달  
- Router (L3)  
  - IP address를 보고 판단해서 전달
- Switch (L2)
  - mac address기반 local network에서 wifi에서 다른 Link로 전달  
  - 무선 -> 유선 하위 Layer로 갈수록 빠른 경향이 있으므로 link interface 수준에서 전달

### Network Service Model
- network layer이 상위 계층에게 보장해주는 service
  - Guaranteed delivery: packet이 상대방에게 전달
  - Guaranteed delivery with bounded delay: 특정 지연시간 기준 내에 반드시 전달
    - 우선순위에 따라서 service를 다르게
  - In-order packet delivery: 순서대로 전달
    - 경로가 다른 경우가 있을 수 있음 -> 순서 달라짐
  - Guaranteed minimal bandwidth: 전송률 보장
  - Guaranteed maximum jitter: 안정적으로 데이터 전달 -> 다음 패킷이 시간내(inter-arrival time)에 들어오지 않으면 discard 
    - real time service (인터넷 전화)
- Datagram networks
  - Connectionless service
  - routing할 때 미리 명시 되어 있지 않음 -> router들이 알아서 -> 일반적
  - connection이 되어 있지 않는 경우
- Connection-oriented services
  - 명시적으로 router 설정  

### Internet protocol
- DARPA - IETF 표준, internet project를 통해 최초로 개발
- IP -> layer 3 protocol
- Connectionless operation -> 상대방 entity와 연결맺는 개념이 없고, 그때 맞는 route 설정  
  - flexable, overhead(경로 저장하고 있어야 함, 다른 패킷들을 block) 덜 함
- L1/L2 어떤 거든지 수용 가능 
  - IP가 얹어지면 네트워크 형성 (서로 데이터 전달할 수 있게 함)
  - 강력한 호환성
- Routing protocol: forwarding table 형상  
- IP protocol: datagram 전달    
  - Version number: IPv4, IPv6 와 같은 IP protocol 이름 (header의 앞부분)
  - header length: header가 언제 끝나는지
  - type of service(TOS): IP datagram의 유형
  - Datagram length: data 포함한 길이 (전체 길이)
  - Identifier, flag, fragmentation offset: IP fragment 관련제어정보
  - Time to live
    - datagram 이 얼마만큼 살아있을 수 있는가 -> 수신 측에 도착하지 못한 패킷이 네트워크에 남으면 네트워크 자원을 잠식할 수 있기 때문에 방지하기 위한 옵션 
  - Protocol: TCP/UDP  
  - header checksum: header의 parity  
  - IP address: IP protocol 에서 사용하는 주소 (32bit -> 2^32주소 존재)    
  - 유선, 무선에 따라 다른 IP 주소 존재  
    - Source IP address: 보내는 end-node의 주소    
    - Destination IP address: 받는 end-node의 주소    
    - Domain Name Server: domain name (www.naver.com) -> IP address (125.209.222.141)    
  - subnet: 주변의 host 그룹 -> 물리적으로 비슷한 위치  
- ICMP(Internet control management) protocol: 관리를 위한 부가적 절차    

> IPv4 Addressing
  - 32bit 주소
  - host 나 router의 interface(link) 별로 존재
  - 하나의 컴퓨터여도 유선, 무선에 따라서 address 다름 
  - Dotted-decimal notation: 8bit, 0 ~ 255
  - internet내에서 globally unique
  - NAT: locally unique
  - subnet: network 관점에서 그룹으로 묶이는 주변의 host그룹 -> IP 주소 앞부분 24bit
  - 물리적으로 비슷한 위치에 있음 -> 같은 subnet으로 묶어줌 -> network를 중복없이 효율적으로 관리 가능

### CIDR - Classless Interdomain Routing
- subnet: IP주소 앞부분 지칭
  - 223.1.1.0/24 -> 앞 24bit의 IP가 subnet 주소에 해당
- Gateway: subnet에 접근할 때 대문 역할 
  - 보통 IP 끝자리 1
  - subnet에 유입되는 packet을 가장 먼저 받아서 처리
  - subnet에 생성된 packet이 최종적으로 나가는 관문

### IP Assignment
- subnet 내에서 포함될 수 있는 host 수
  - 32 - 24 -> 8 bit 2^8 = 256
  - 1 -> gateway /0 / 255 -> broadcast 256-3 = 253 : 할당가능한 address
- ISP (internet service provider) = KT, SKT 유선 서비스 제공하는 애들이 IP 할당해줌
  - 집 -> 1,2개 / 기관 -> 24bit 의 subnet
  - 전산실에서 수동으로 설정/ DHCP(Dynamic Host Configuration Protocol)로 자동으로 할당

### IP Datagram Fragment
- Link에 따라서 transmission이 달라짐
- 링크마다 MTU(Maximum Transmission Unit)가 다름
- router의 역할
- destination의 IP의 서비스 계층으로 올라가기 전에 reassemble 되어야 함

![image](https://user-images.githubusercontent.com/50178026/114542196-9beb9d00-9c92-11eb-8b0e-ff7ccd340616.png)

```  
- fragment의 순서를 잘 맞춰야 함 -> IP header에 명시  
- flag 0 = fragment 되어 있지 않다  
- flag 1 = fragment 되어있다  
- offset = 몇번째 fragment  
- ID, identifier = 어떤 datagram에서 유래가 되었는가  
```  

### ICMP (Internet Control Message Protocol)
- router 간에서 L3 수준에서 메시지 주고 받음
- 실제 data 전달이 아니라 연결 상태 확인하기 위해서 활용 -> 동작하는지 안하는지  
- ping
- ICMP type: 0 -> ping 받음
- ICMP type: 8 -> request
- ICMP type: 3 -> 전달할 수 없는 상황

### IPv6  
- IPv4(32bit, 약 40억개)의 IP 주소가 모자람 
  - 2 level addressing은 비효율적
  - 비어있는 IP도 많음  
  - 많은 애들이 IP 할당받기 어려워징 -> QoS 지원 어려워짐
- IP 주소 32 -> 128bit  
- 40bit 고정 크기의 header
- header checksum 없음 -> L1,L2 error control하기 때문에    
- flow 별 quality of service 관리 가능 -> 모든 연결에 대해서 IP할당 가능하기 때문에   
- IPv4 -> IPv6 넘어가는데 overhead, cost가 많이 듦  
- NAT, subset으로 인해서 IPv4를 개선해서 사용함  

## Routing
- Router 내의 data plane -> data forwrading / IP header 분석 / MTU기반으로 fragmentation  
- control plane -> routing / ICMP(protocol management)  

## Routing algorithm
- Router forwarding table
  - source -> destination 
  - router에 어디로 가야한다는 forwarding table이 있음  
  - destination 쪽에서 부터 routing 정보가 올라와서 gateway는 routing 정보를 읽어서 router에게 보냄    
  - decentralized: router들 각각이 주체적으로 만들 수 있음  
- Countol plane: routing 알고리즘을 통해 forwarding table 생성
- Data plane: forwarding table을 이용해서 동작
- Routing algorithm의 목적: Source router -> Destination router : 최적의 경로를 찾는 것
- graph theory를 기본적으로 활용
- Routing Path
  - Least-cost path: cost의 합이 최소
  - Shortest path: 가장 짧은 path / 모든 cost가 같은 경우
- Centralized routing algorithm (= Global): 모든 경우의 수 대해 해봄
  - 전체 네트워크에 대한 지식있어야 함
  - Link-state algorithm
- Decentralized : router들이 자신의 상태에 맞게 최적의 routing path 결정
  - 특정 노드가 모든 링크에 대한 정보를 완벽하게 가지지 않으며 주로 자신과 연결된 링크에 대한 정보만 가지고 단계적으로 동작함
  - Iterative, distributed manner
  - Distance-vector algorithm

### Dynamic routing
- Traffic load(에 대해 크게 변하지 않음), topology 변화(어떤 router가 망가졌을 때 돌아감)에 의해 자동으로 routing table 변함
- 문제점: Routing loop(보냈는데 다시 돌아옴), oscillation(옆에 a,b 있는데 a가 널널해서 보냈는데 b가 널널해져서 b로 보냄 a,b 핑퐁)

### Static routing
- routing table이 시간에 따라 거의 변하지 않음
- 사람의 조직에 의해서만 변함
- edge에 좀 더 가까이, static 선택하는 편
- 회사 내부망 -> static
- Fixed Routing
  - = Permanent route: 고정으로 routing 규칙 지정  
  - IP address 특정 대역 등에 대해 out-port 지정
  - 내부망의 경우, 건물마다 나눠 줌 
  - input으로 들어온 패킷을 어떤 건물 (고정 ip 존재)로 보낼 때 사용 
  - 비교적으로 간단하지만 flexibility가 적고 수고가 많이 듦
  - 붐비는 상황에 취약함

### Flooding
- static -> dynamic 의 중간
- 효과적인 방식이지만 네트워크 자원을 많이 먹음
- 모든 neighbor에게 packet 뿌림
- 하나의 port 사용하지 않음
- destination은 multiple copy 형태로 수신
- routing protocol, table 필요 없음
- 재전송에 대한 제어 필요, traffic load 불필요하게 큼 
- subnetwork 단위는 가능
- global router에서 사용하기 어려움

### Link State Routing  
- Centralized routing algorithm (= 모든 애들이 broadcasting을 통해서 모든 것을 알고 있음)
- 모든 node가 동일한 routing 정보를 공유할 수 있음
- Dijkstra's algorithm 사용
- 모든 link cost 파악
- Link-tstate broadcast algorithm -> 각자의 iedntity와 link 별 cost 정보를 neighbor에게 전달
- 한번에 routing table 만들 수 있음
- 모든 node가 동일한 routing 정보 공유 가능
- Congestion- sensitive routing 과정에서 oscillation 
  - 최저를 찾다가 routing 경로가 안정하지 못하고 왔다갔다 할 수도 (cost 변화로 인해)

### Distance-Vectort Routing
- Decentralized routing algorithm
- Bellman-Ford equation 활용
- asynchronous/ distributed : 각자 알아서
  - 멀리 있는 애들 상관없이 neighbor node끼리 정보 주고 받으면서 routing 계산
- 중간에 cost가 변경되면 routing loop 발생할 수 있음 
- DV
  - 인접 노드만 정보 교환 -> 정보 교환의 cost가 적음
  - 변화 빈도에 따라 메시지 전송 횟수 큼
  - convergence 속도 느림
- LS
  - 모든 노드 정보 교환 -> control overhead 큼
  - 메시지 전송 많음
  - Robustness: 각자 routing 계산하는 방식이라 더 안정적

### Hierarchical Routing
- 각각의 알고리즘이 장단점 가지고 있기 때문에 일반적으로 사용하는 방식
- 실제 인터넷은 sub-network와 gateway router 형태로 계층적으로 네트워크 형성
  - sub-network 내: intra -AS routing 
  - network- network: inter -AS routing 
- AS(Autonomous System): 하나의 sub-network의 모음

### RIP - routing information protocol
- intra -AS routing
- Distance-vector protocol
- link cost 모두 1
- Hop count가 cost metric
- 30초에 한번씩 routing 정보를 broadcasting 함
- 180초 이상 routing advertisement message 수신이 없으면 
  - No longer reachable이라 판단
  - Routing table 수정하고 advertisement 보냄 
- 응답하지 않으면 응답하라고 UDP, port520으로 보냄  

### OSPF - Open Shortest Path First
- RIP 다음 버전
- Dijkstra lesat-cost path 기반 -> flooding 활용
- flooding 활용 (나에게 연결된 모든 Port에게 뿌림)
- Complete topological map 기반으로 계산
- robustness를 위해 link change가 없어도 최소한 30분에 한번 수행

### BGP - Border Gateway Protocol
- AS 간 routing을 위한 protocol 
- 인접 AS간 sub network에게 도달할 수 있는지 교환
- reachability 정보 -> 안에서는 packet forwarding 일어남  
- subnet에 대한 routing 결정  

<br>

**- Routing performance -> Correctness, Optimality**    
**- Low control overhead -> Simplicity, Efficiency**
