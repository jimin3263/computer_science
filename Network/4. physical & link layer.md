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

|7-layer|
|:---:|
|Application|
|presentation|
|session|
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
- center freq = 대역   
- spectrum = 주파수에 따라 다양한 신호   
- bandwidth: 신호가 차지하는 공간   
- 아날로그, 디지털 -> 전자기파 (차지하는 공간: bandwidth, 이 공간에서 생겨먹은 모양: spectrum)  

### Encoding/ Decoding
- modulation: 신호 만들어서, 어떤 채널을 통해서 demodulation을 통해서 정보를 얻어냄  
- encoding: 보내고자하는 bit열을 보내기 쉽게 가공
- decoding: 복원 처리
- error detection: N bit를 추가로 붙임 (CRC)
- error correction: bit수가 n배가 됨 (FEC) 
- scrambling: 비트를 뭉개서 robust하게 정보 전송
- multiplexing: 여러 개의 SDU합침
- repetiton: 똑같은 비트 여러 개
- puncturing: 비트 스트림 중에 몇 개 뺌  
*-> 다양한 동작을 하는게 encoding / 역 동작: Decoding*

### Network 공학 관점  
- black box로 간주  
- api 기반으로 해서 L1,L2에서 보냄
- 어떤 인터넷 망에서 어떤 IP를 가진 애들한테 전달하는 지가 더 중요
- 어떤 물리 계층을 통해서 전달되는 지는 중요하진 않음  

### Analog/ Digital  
- 음성(Analog) -> analog (옛날)
- Analog -> Digital -> Analog (현재)  
- 변조된 신호는 analog지만, 그 전에 상태에 따라서 통신이 달라짐  
- Analog
  - continuous해서 조금만 노이즈가 들어오더라도 신호자체가 크게 변화함 -> FEC, CRC 기술 적용하지 못함
- Digital
  - noise에 강함
  - 압축
  - modulation, demodulation 실시간 가능
  - 강력한 보안 기술의 적용 (비트 스트림)  

## Physical layer에서의 다양한 매체  
### Wired/ Wireless
- wired: Twisted pair -랜선, optical fiber- 광선
  - 물리적 제약 큼
  - 대역폭이 작은 대신 감쇄가 적고 noise 대비 수신 신호 크기가 큼  
  - attenuation(감쇄)가 적음  
- Twisted pair (가장 대중적)
  - 2가닥이 꼬여있는 구조
  - Unshielded Twisted Pair(UTP) -> 로컬/ Shielded Twisted Pair(STP)  
  - 성능 특성에 따라 카테고리로 나뉨
- Coaxial cable: 동축
  - twisted pair 보다 도달거리 길고 비쌈
  - 구리
- Optical fiber
  - 주파수 range큼
  - 국가 간의 통신
  - Gbps급 속도, 가볍고 작은 사이즈
  - 통신 인프라로 활용
- Unguided media(air, vacuum, seawater)
  - 활용 가능한 주파수의 확보가 필요함
  - 시골, 도시에서 전달하는 지에도 영향
  - 장애물에 의한 감쇄가 심함
  - bandwidth가 커질 수록 frequency selectivity(주파수 특성)에 의한 왜곡 심해짐 

### Duplex - 어떻게 전달할 것인가
: 송수신 방향에 대한 환경
- Simplex: 단방향 
  - 방송국(송출) - 디바이스(수신)
- Half duplex: 양방향, 한 순간에 한 방향
  - 무전기
  - 워키토키
  - Time Division Duplex(TDD): 시간을 아주 잘게 쪼갬
- Full duplex: 양방향, 한 순간에 동시에 양방향 
  - modem 두 개 필요 -> 하드웨어적으로 비효율  
  - 보내는 신호가 받는 신호로 들어올 수도 있음
  - 아직 개발 중

### 성능 지표
- Data rate: 시간당 bit 전송률  
  - 얼마만큼 보낼 수 있는가 
  - Bandwidth 반영됨 
- Throughput: 전송률  
- Spectral efficiency: Hz당 전송률
  - 주파수 효율 -> 안테나 여부, 채널코딩 
  - data rate/ bandwidth
- BER(Bit Error Rate): bit 오류율
  - bit에 에러가 날 수 있는 확률
- Channel Capacity
  - error가 일정 수준 이하로 유지하면서 낼 수 있는 이론 상의 최대 전송률    
  - SNR 기반 = signal to noise ratio(신호 대 잡음비) -> 높을 수록 잡음이 덜함
  - SNR가 높을 수록 data rate 높아짐

# Layer2 - Data Link Layer 
: 유선, 와이어리스 채널 Media Access Control
: link 자체 control  
- L1 물리 계층에서의 정보 전송 흐름을 제어하는 역할
  - 보낼까 말까, 얼만큼 보낼까/ 상위 계층으로 수신 PDU를 올릴까 말까
  - L1이 보낼 수 있는 capacity와 밀접하게 연관
- L2는 제대로 보냈는지 관리
  - Packet Error
  - r(t)= N(t)+s(t)
  - 수신 신호에 더해져서 demodulation, decoding에 영향을 줌
  - single-bit errors: 간헐적인 noise 증가로 분산된 bit가 각각 오류가 난 경우
  - burst errors: 수신 신호 감도가 낮아지면서 연속적으로 bit error 발생
  - L2 -> 스스로 알지 못함 -> parity bit 
  - 원래 보낸 packet과 비교할 때 1 bit라도 다름
  - S(t) > N(t) (충분히) 크면 패킷 에러는 줄일 수 있음

### Error Detection
- parity bit: 추가적인 bit
- TX + parity -> RX: parity가 몇 비트인지, 어떤 규칙인지 앎 -> 생성 규칙에 따라 parity를 만들어보고 같으면 에러 없다고 판단  
  - odd/ even parity -> 1 bit를 붙여서 홀수, 짝수를 만듦
  - 2-dimensional parity check -> 한쪽이 다 홀/ 짝
  - *-> 둘 다 요즘 사용하지 않음*  
  - checksum: 
    - 16bit 단위로 끊어서 누적 덧셈-> 1의 보수 = parity
    - 수신 측에서 동일한 덧셈을 해서 parity를 더했을 때 FFFF가 나오면 error 없음
  - CRC 방식: 
    - 가장 강력하고 두루 활용되는 방식 -> 나머지 붙임
    - 미리 정해진 다항식으로 나눔 
    - bit 열이 아닌 다항식으로 나타내기도 함 -> 지수를 통해서 몇번째 비트인지 순서를 쉽게 알 수 있음
    
### Error Correction  
- FEC (Forward error corretion)
- bit 내 에러가 나더라도 원래 데이터로 복호화할 수 있는 형태
- parity를 통해서 에러가 발생하면, 재전송 -> 에러를 스스로 고치는 게 젤 좋음 
- channel encoding: data bit를 재가공해서 고칠 수 있는 형태 (codeword)
- channel decoding: encoding된 bit를 decoding해서 원래 bit 복원 (이 과정에서 error 정정이 일어남)
- link가 좋을때 -> 에러가 많이 안일어나니까 code rate를 높게 
- link가 좋지 않을때 -> 에러가 많이 일어나므로 n비트를 최대한 많이 늘려서 에러가 나더라도 복호화를 잘할 수 있음
  - code rate: k/n(원래 데이터 크기/ code word 크기) -> codeword 크기 작게 함
- 종류
  - block code: block단위로 새롭게 배치
  - convolutional code & turbo code
  - LDPC: 이론상의 한계치에 가장 근접
  - Polar code
- 효과
  - target BER를 만족하는 SNR을 낮출 수 있는 효과  

### Error Control  
- FEC를 통해서 error 수정 시도 -> error detection을 통해 error 여부 -> 재전송 요청 ARQ
- ARQ: unreliable data link를 reliable 하게 만들어주는 역할
  - stop and wait / slide-window(Go Back N) / Selective-Reject
  - L2 -> Frame 단위임 
- hybrid ARQ (HARQ)
  - FEC가 포함됨
  - error packet 재활용
  - 재전송된 packet을 잘 결합해서 Forward Error Correction을 함

### Media Access Control - MAC
- N:1 (여러 개의 디바이스가 하나의 매체 사용)
- 붐비는 채널을 누가 먼저 사용할 것인가?
- Non contenion 기반 MAC
  - 중앙 집권적(기지국)이 스케줄링
  - scheduling -> 어떤 디바이스가 어떤 주파수, 시간 통해서 전송을 할지 / 자원을 사용해서 보낼지  
  - link 자원을 사이좋게 나눠서 사용하는 방식
  - L1 전송이 충돌하는 일이 없음
  - Multiplexing: 여러 신호가 한번에 합쳐져서 보낸다  
  - Frequenct Division Multiplexing: 주파수 나눔
  - Time Division Multiplexing: 시간 나눠서
  - Wavelength Division Multiplexing: wavelength 나눔 
  - Code Division Multiplexing: 신호를 보낼때 코드를 곱해서 보냄 ,,?  
- Contention 기반 MAC 
  - 경쟁 기반
  - wifi
  - LTE/ 5G -> 쉬고 있는 휴대폰 -> 기지국에게 있는 것을 알려줘야 함, 꺼져있어서 어떤 자원을 내려줘야 할지 모름 -> 접속을 하려고 하는 디바이스 여러 개, 경쟁하게 됨
  - ALOHA (slotted ALOHA) : Random Access Channel -> slot을 나눠서 slot 중에서 하나의 시간에 보내라 : 충돌 여전히 발생  
  - IEEE 계열 MAC(ethernet, WIFI)
    - 유선 (CSMA/CD): 충돌된 걸 바로 알 수 있음 (carrier Detection)
    - 무선 (CSMA/CA): 보내는 동안은 모름, ACK 오지 않음 -> 기다렸다가 보냄 -> 충돌로 인한 오버헤드 줄임  

*-> 표준에 따라서 L1,L2,L3 에서 하는 역할이 좀 달라졌음*  
*-> 서로의 기능이 확연하게 구분되지 않음*
