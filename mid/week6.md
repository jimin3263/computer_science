# Layer3 - Internet Protocol  

- Layer2 - 전송 수행, error control  
- Layer1 - 물리적 전송  
-> hop 1개일 때 수행  

### Layer3
- link가 여러개 있을 때 source -> destination 어디로 데이터 전달할 것인지  
- 전달하고자 하는 패킷이 destination으로 향하는 경로를 찾는 역할  
- sub network 간 이동
- Router: packet forwarding을 주 역할로 하는 구성 요소 -> network 내에서 적잘한 방향으로 packet 전달
- Forwarding: input -> output (forwarding table 기반)
- Routing: path를 파악하기 위한 제어 동작(경로 찾음) -> forwarding table 만듦  
- Forwarding table: header value -> output link
- Router: L3관점(IP)에서 능동적인 역할  

### Hub vs Router
- Hub (L1): physical layer 신호가 여과 없이 그대로 연결된 모든 end-node -> 신호 증폭기(노이즈가 낄 수 있으니까)  
  - 데이터가 왔으니까 그대로 전달  
- Router (L3)  
  - IP address를 보고 판단해서 전달
- Switch (L2)
  - mac address기반 local network에서 wifi에서 다른 링크로 전달  

### Network Service Model
- Guaranteed delivery: packet이 상대방에게 전달
- Guaranteed delivery with bounded delay: 특정 지연시간 기준 내에 반드시 전달
- In-order packet delivery: 순서대로 전달
- Guaranteed minimal bandwidth: 전송률 보장
- Guaranteed maximum jitter: 안정적으로 데이터 전달 -> 다음 패킷이 시간내(inter-arrival time)에 들어오지않으면 discard  
<br>

- Datagram networks
  - Connectionless service: routing할 때 미리 명시 되어 있지 않음 -> router들이 알아서 -> 일반적
- Connection- oriented services
  - 명시적으로 router 설정  

### Internet protocol
- DARPA - IETF 표준
- IP -> network 3 protocol
- Connectionless operation -> 상대방 entity와 연결맺는 개념이 없고, 그때 맞는 루트 설정  
- flexable, overhead(경로 저장하고 있어야 함, 다른 패킷들을 block)덜함
- L1/L2 어떤 거든지 수용가능 -> IP가 얹어지면 네트워크 형성 -> 서로 데이터 전달할 수 있게 함  

<br>  

- protocol 종류  
> Routing protocol: forwarding table 형상  

> IP protocol: datagram 전달    
- IPv4- IP프로토콜 이름 (header의 앞부분)
- header length: header가 언제 끝나는거
- type of service(TOS): IP datagram의 유형
- Datagram length: data 포함한 길이 (전체 길이)
- Identifier, flag, fragmentation offset: IP fragment 관련제어정보
- Time to live: 데이터그램이 얼마만큼 살아있을 수 있는가 -> 수신 측에 도착하지 못한 패킷이 네트워크에 남으면 네트워크 자원을 잠식할 수 있기 때문에 방지  
- Protocol: TCP/UDP  
- header checksum: header의 parity  
- IP address: IP protocol 에서 사용하는 주소 (32bit -> 2^32주소 존재)    
- 유선, 무선에 따라 다른 IP 주소 존재  
  - Source IP address: 보내는 end-node의 주소    
  - Destination IP address: 받는 end-node의 주소    
  - Domain Name Server: domain name -> IP address    
- subnet: 주변의 host 그룹 -> 물리적으로 비슷한 위치  

> ICMP(Internet control management) protocol: 관리를 위한 부가적 절차    

- IPv4 Addressing
  - subnet: network 관점에서 그룹으로 묶이는 주변의 host그룹 -> IP 주소 앞부분 24bit
  - Gatewat: subnet에 접근할 때 대문 역할 -> 보통 끝자리 1
  - 물리적으로 비슷한 위치에 있고 L1/L2 수준에서 같은 영역에 있음

### IP Assignment
  - subnet 내에서 포함될 수 있는 host 수
  - 32 / 24 -> 8 bit 2^8 = 256
  - 1 -> gateway /0 / 255 -> broadcast 256-3 = 253
  - ISP (internet service provider) = KT, SKT 유선 서비스 제공하는 애들이 IP 할당해줌
  - 집 -> 1,2개 / 기관 -> 24bit 의 subnet
  - 전산실에서 수동으로 설정/ DHCP(dynamic host configuration protocol)로 자동으로 할당

### IP Datagram Fragment
- Link에 따라서 transmission이 달라짐
- 링크마다 MTU(Maximum Transmission Unit)가 다름
- router의 역할
- destination의 IP의 서비스 계층으로 올라가기 전에 reassemble 되어야함

![image](https://user-images.githubusercontent.com/50178026/114542196-9beb9d00-9c92-11eb-8b0e-ff7ccd340616.png)

- fragment의 순서를 잘 맞춰야함 -> IP header에 명시  
- flag 0 = fragment 되어 있지 않다  
- flag 1 = fragment 되어있다  
- offset = 몇번째 fragment  
- identifier = 어떤 datagram에서 유래가 되었는가  

### ICMP (Internet Control Message Protocol)
- router 간에서 L3수준에서 메시지 주고 받음
- 실제 data 전달이 아니라 연결 상태 확인하기 위해서 활용 -> 동작하는지 안하는지  
- ping
- ICMP type: 0 -> ping 받음
- 8 -> request
- 3 -> 전달할 수 없는 상황

### IPv6  
- IP주소가 모자름  
- 비어있는 IP도 많음  
- IP 주소 32 -> 128bit  
- header checksum 없음 -> L1,L2 error control하기 때문에    
- flow 별 quality of service 관리 가능 -> 모든 연결에 대해서 IP할당 가능하기 때문에   
- IPv4 -> IPv6 넘어가는데 overhead, cost가 많이 듦  
- NAT, subset으로 인해서 IPv4를 개선해서 사용함  
