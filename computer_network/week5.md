# Layer1 - Physical Layer  

### OSI Layer 
: 통신 네트워크를 이루는 Protocol layer 표준형 모델  

|5-layer|
|:---:|
|Application|
|Transport|
|Network|
|Link|
|Physical|

- L1/L2 는 보통 쌍임 (LTE L1/L2)  
- L3는 사실상 IP (Network)
- L4는 사실상 TCP/UDP (Transport)

### Layer1
- link라고 하는 물리 매체에 따라서 동작을 하도록 함  
- 무선 -> 어떤 주파수?  
- 1:1 상황에서 직접적인 물리 신호를 주고 받아서 정보를 전달하는 역할
- 연결 형태에 맞게 실제 물리 신호를 적절하게 생성
- 1 hop(하나의 연결)으로 연결된 상대방에게 직접 정보 전달
- 입력: SDU
- 출력: 물리적인 아날로그 신호 (PDU 형태가 아님)

### Modulation/ Demodulation
- TX -> RX
- Modulation: 송신할 정보를 carrier signal에 담는 처리
- Demodulation: 수신된 변조 신호로부터 원래 송신 정보를 복원   
![image](https://user-images.githubusercontent.com/50178026/114513460-2c1ae980-9c75-11eb-9638-489a7c15c1be.png)  
- center freq = 대역   
- spectrum = 주파수에 따라 다양한 신호   
- bandwidth: 신호가 차지하는 공간   
- 아날로그, 디지털 -> 전자기파 (차지하는 공간: bandwidth, 이 공간에서 생겨먹은 모양: spectrum)  

### Encoding/ Decoding
- modulation: 신호 만들어서, 어떤 채널을 통해서 demodulation을 통해서 정보를 얻어냄  
- 실제 그 정보가 modulation가기 전에 encoding, decoding 단계가 더 있음 -> error detection을 위한 parity   
- FEC/CRC을 하는 역할  
- scrambling: 비트를 뭉개서 roverse하게 정보 전송
- multiplexing: 여러개의 SDU합침
- repetiton: 똑같은 비트 여러개
- puncturing: 비트 스트림 중에 몇개 뺌
-> 다양한 동작을 하는게 encoding / 역 동작: Decoding    

### Network 공학 관점  
- black box로 간주  
- api 기반으로 해서 L1,L2에서 보냄
- 어떤 인터넷 망에서 어떤 IP를 가진 애들한테 전달하는 지가 더 중요
- 어떤 물리 계층을 통해서 전달되는 지는 중요하진 않음  

### Analog/ Digital  
- 음성(Analog) -> analog (옛날)
- Analog -> Digital -> Analog (현재)  
- 변조된 신호는 analog지만, 그 전에 상태에 따라서 통신이 달라짐  
- Digital
  - noise에 강함
  - 압축(modulation, demodulation)  
  - 강력한 보안 기술의 적용 (비트 스트림)  

### Wired/ Wireless
- wired: Twisted pair -랜선, optical fiber- 광선
  - 물리적 제약 큼
  - 대역폭이 작은 대신 감쇄가 적고 noise 대비 수신 신호 크기가 큼  
  - 주파수 range가 큼
  - attenuation(감쇄)가 적음  
- Twisted pair
  - Unshielded Twisted Pair(UTP)/ Shielded Twisted Pair(STP)  
  - 카테고리로 나뉘어짐
- Coaxial cable
  - twisted pair 보다 도달거리 길고 비쌈
  - 구리
- Optical fiber
  - 국가간의 통신
  - Gbps급 속도  
- Unguided media(air, vacuum, seawater)
  - 장애물에 의한 감쇄가 심함
  - bandwidth가 커질 수록 frequency selectivity에 의한 왜곡 심해짐 

### Duplex  
: 송수신 방향에 대한 환경
- Simples: 단방향
- Half duplex: 양방향, 한 순간에 한 방향
  - 무전기
- Full duplex: 양방향, 한 순간에 동시에 양방향 
  - 모뎀 두개 필요 -> 하드웨어적으로 비효율  

### 성능 지표
- Data rate: 시간당 bit 전송률  
  - 얼마만큼 보낼 수 있는가  
- Throughput: 전송률  
- Spectral efficiency: Hz당 전송률  
- BER: bit 오류율
- Channel Capacity: error가 일정 수준 이하로 유지하면서 낼 수 있는 이론 상의 최대 전송률  

# Layer2 - Data Link Layer 
: 유선, 와이어리스 채널 access control
: link 자체 control  
- L1 물리 계층에서의 정보 전송 흐름을 제어하는 역할
  - 보낼까 말까/ 얼만큼 보낼까 -> L1이 보낼 수 있는 capacity와 밀접하게 연관
- L2는 제대로 보냈는지 관리
  - Packet Error
  - N(t)+S(t)
  - 원래 보낸 packet과 비교할 때 1 bit라도 다름
  - S(t)>N(t) (충분히) 이면 패킷 에러는 줄일 수 있음

### Error Detection
- parity bit: 추가적인 bit
- TX + parity -> RX: parity가 몇 비트인지, 어떤 규칙인지 앎 -> 생성 규칙에 따라 parity를 만들어보고 같으면 에러 없다고 판단  
  - odd/ even parity -> 1 bit를 붙여서 홀수, 짝수를 만듦
  - 2-dimensional parity check -> 한쪽이 다 홀/ 짝
  - checksum: 16bit 단위로 끊어서 1의 보수 취함 -> 계속 해서 더함 parity 구함 -> 더해서 FFFF가 나오도록  
  - CRC 방식: 가장 강력하고 두루 활용되는 방식 -> 나머지 붙임

### Error Correction  
- FEC (Forward error corretion)
- bit 내 에러가 나더라도 원래 데이터로 복호화할 수 있는 형태
- parity를 통해서 에러가 발생하면, 재전송 -> 에러를 스스로 고치는 게 젤 좋음(encoding/decoding에서 하는 역할)  
- link가 좋을때 -> 에러가 많이 안일어나니까 code rate를 높게 : codeword크기 작게함
- 효과
  - target BER를 만족하는 SNR을 낮출 수 있는 효과  

### Error Control  
- FEC를 통해서 error 수정 시도 -> error detection을 통해 error 여부 -> ARQ 
- ARQ: unreliable data link를 reliable 하게 만들어주는 역할
  - stop and wait / slide-window(Go Back N) / Selective-Reject
  - L2 -> Frame 단위임 
- hybrid ARQ: error packet 재활용

### Media Access Control - MAC
- 붐비는 채널을 누가 먼저 사용할 것인가?
- Non contenion 기반 MAC
  - 중앙 집권적(기지국)
  - scheduling -> 어떤 디바이스가 어떤 주파수, 시간 통해서 전송을 할지 / 자원을 사용해서 보낼지  
  - link 자원을 사이좋게 나눠서 사용하는 방식
  - Multiplexing: 여러신호가 한번에 합쳐져서 보낸다  
  - Frequenct Division Multiplexing: 주파수 나눔
  - Time Division Multiplexing: 시간 나눠서
  - Wavelength Division Multiplexing: wavelength 나눔 
  - Code Division Multiplexing: 신호를 보낼때 코드를 곱해서 보냄 ,,?  
- Contention 기반  
  - 경쟁 기반
  - wifi
  - LTE/ 5G -> 쉬고 있는 휴대폰 -> 기지국에게 있는 것을 알려줘야함, 꺼져있어서 어떤 자원을 내려줘야할지모름 -> 접속을 하려고 하는 디바이스 여러개, 경쟁하게 됨
  - ALOHA (slotted ALOHA) : Random Access Channel -> slot을 나눠서 slot 중에서 하나의 시간에 보내라 : 충돌 여전히 발생  
  - IEEE (ethernet, WIFI): 유선 -> 충돌된 걸 바로 알 수 있음 (carrier Detection)
  - 무선 (CSMA/CA): 보내는 동안은 모름, ACK오지 않음 -> 기다렸다가 보냄 -> 충돌로 인한 오버헤드 줄임  

-> 표준에 따라서 L1,L2,L3 에서 하는 역할이 좀 달라졌음  
