## Routing Control Plane
- Router 내의 data plane -> data forwrading / IP header 분석 / MTU기반으로 fragmentation  
- control plane -> routing / ICMP(protocol management)  

## Routing algorithm
- Router forwarding table
  - source -> destination 
  - router에 어디로 가야한다라는 forwarding table이 있음  
  - destination 쪽에서 부터 routing 정보가 올라와서 gateway는 routing 정보를 읽어서 router에게 보냄    
  - decentralized: router들 각각이 주체적으로 만들 수 있음  
- Countol plane: routing 알고리즘을 통해 forwarding table 생성
- Data plane: forwarding table을 이용해서 동작
- Source router -> Destination router : 최적의 경로를 찾는 것
- graph theory를 기본적으로 활용
- Routing Path
  - Least-cost path: cost의 합이 최소
  - Shortest path: 가장 짧은 path / 모든 cost가 같은 경우
- Centralized routing algorithm (= Global): 모든 경우의 수 대해 해봄
  - 전체 네트워크에 대한 지식있어야 함
  - Link-state algorithm
- Decentralized: router들이 자신의 상태에 맞게 최적의 routing path 결정
  - 특정 노드가 모든 링크에 대한 정보를 완벽하게 가지지 않으며 주로 자신과 연결된 링크에 대한 정보만 가지고 단계적으로 동작함
  - Iterative, distributed manner
  - Distance-vector algorithm

### Dynamic routing algorithms
- Traffic load, topology 변화(어떤 router가 망가졌을때 돌아감)에 의해 자동으로 routing table 변함
- Routing loop(보냈는데 다시 돌아옴), oscillation(옆에 a,b 있는데 a가 널널해서 보냈는데 b가 널널해져서 b로 보냄 a,b 핑퐁)

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
- 효과적인 방식, 네트워크 자원을 많이 먹음
- 모든 neighbor에게 packet 뿌림
- destination은 multiple copy 형태로 수신
- routing protocol, table 필요 없음
- 재전송에 대한 제어 필요, traffic load 불필요하게 큼 
- global router에서 사용하기 어려움

### Link State Routing  
- Centralized routing algorithm
- Dijkstra's algorithm 사용
- 모든 link cost 파악
- Link-tstate broadcast algorithm -> 각자의 iedntity와 link 별 cost 정보를 neighbor에게 전달
- 한번에 routing table 만들 수 있음
- 모든 node가 동일한 routing 정보 공유 가능
- Congestion- sensitive routing 과정에서 oscillation 
  - 최저를 찾다가 routing 경로가 안정하지 못하고 왔다갔다 할 수도

### Distance-Vectort Routing
- Decentralized routing algorithm
- Bellman-Ford equation 활용
- asynchronous/ distributed : 각자 알아서
  - 멀리 있는 애들 상관없이 neighbor node끼리 정보 주고 받으면서 routing 계산
- 중간에 cost가 변경되면 routing loop 발생할 수 있음 

- DV
  - 정보 교환의 cost가 적음
  - 변화 빈도에 따라 메시지 전송 횟수 큼
  - convergence 속도 느림
- LS
  - control overhead 큼
  - 메시지 전송 많음
  - Robustness: 각자 routing 계산하는 방식이라 더 안정적

### Hierarchical Routing
- 각각의 알고리즘이 장단점 가지고 있기때문에 일반적으로 사용하는 방식
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
- Dijkstra lesat- cost path 기반 
- flooding 활용 (나에게 연결된 모든 Port에게 뿌림)
- Complete topological map 기반으로 계산
- link change가 없어도 최소한 30분에 한번 수행

### BGP - Border Gateway Protocol
- AS 간 routing을 위한 protocol 
- 인접 AS간 sub network에게 도달할 수 있는지 교환
- reachability 정보 -> 안에서는 packet forwarding 일어남  
- subnet에 대한 routing 결정  

<br>

**- Routing performance -> Correctness, Optimality**    
**- Low control overhead -> Simplicity, Efficiency**

*routing -> table/ distance vector/ link state 기반 / forwarding table 기반으로 forwarding 정도* 
