## 1.1 What Operating Systems Do

### 사용자 관점

|사용자|사용자의 특징|특징|
|------|---|---|
|PC|개인이 사용|사용자 자원 독점, 효율성 상관 없음, 사용 편의성 제공|
|Mainframe, Minicomputer|여러 사람 공유|효율적 관리|
|Workstations|네트워크를 통해 서버 연결|사용 편의성 + 자원의 효율적 관리 |
|Handheld Computer|들고 다님|배터리 등 모바일에 최적화|
|Embedded|기계등 내장, 비교적 간단|사용자 인터페이스 필요 없음|

### 컴퓨터 시스템 관점

- Resource allocator
  - 자원에 대한 관리자, 응용 프로그램이 문제를 해결하기 위해 자원 할당해줌
- Control program
  - 응용프로그램 실행할 수 있게 해줌
  - 사용자 인터페이스 제공(사용자가 원하는 프로그램 쉽게 실행할 수 있도록)
  - 오류, 부적절한 실행 방지를 위해 제어
  - 입출력 장치에 깊이 관여함

### 운영체제

- 다양한 응용 프로그램들이 공통으로 필요하는 입출력 연산, 자원 제어, 할당 공통 기능을 하나의 소프트웨어 통합하는 과정에서 운영체제 
- 운영체제를 설치한 직후 컴퓨터 시스템에 담겨지는 모든것
- windows -> 기본적으로 있는 메모장도 ..?
- kernel: 컴퓨터가 동작되는동안 항상 실행 
- 패키지에 포함되는 내용 중 운영체제의 일부, 커널에 포함될 필요없는 운영체제 -> 시스템 프로그램
- 시스템 프로그램에 관계없는 나머지 프로그램 -> 응용 프로그램
- 운영체제 = kernel
- 컴퓨터 사용자 - 하드웨어 사이에서 중간 역할
  - 운영체제의 목표: 사용자 프로그램 실행, 컴퓨터 시스템을 편리하게 사용, 컴퓨터 하드웨어가 효율적으로 사용됨  

<br>

## 1.2 Computer System Structure

- 운영체제는 하드웨어와 밀접한 연관
- 컴퓨터 시스템 -> 하드웨어, 운영체제, 응용 프로그램, 사용자
- 하드웨어: 컴퓨팅 자원 (CPU, memory, I/O device)
  - CPU: 프로그램/ 프로그램을 이루고 있는 명령 실행
  - main memory: 프로그램이 실행되는 동안 이를 저장하는 장치
  - I/O device: CPU, main memory 제외한 모든것 / 컴퓨터 외부와 데이터를 주고 받거나 데이터 보관
  - CPU-memory / 입출력장치-memory -> bus로 연결 - CPU, 입출력 장치는 memory를 대상으로 경쟁하는 관계  
- 응용프로그램: 사용자의 문제해결 도움, 하드웨어 사용 주체 -> 워드프로세서, 웹 브라우저 등
- 사용자: 컴퓨터 사용, 한 컴퓨터가 다른 컴퓨터 사용할 수도, 서버(클라이언트)  

**-> 사용자는 특정 응용프로그램을 실행함으로써 문제해결을 하고 응용프로그램은 실행을 위해 자원이 필요한데, 운영체제를 통해 응용프로그램은 원하는 자원을 얻을 수 있다**
<br>

- I/O devices 와 CPU 는 독립적으로 동작 -> CPU 명령 시행, 입출력 장치는 입출력 연산
- controller: 입출력 중인 데이터 임시보관하는 로컬 버퍼 가짐 (CPU의 레지스터와 유사)
  - 입력 -> 로컬버퍼 -> 메인메모리
  - 메인메모리 -> 로컬버퍼 -> 출력
- CPU는 메인메모리와 레지스터 간의 데이터 이동
- 입출력 장치는 메인메모리와 입출력 장치의 레지스터인 local buffer간의 데이터 이동
- 위의 두가지 동작은 독립적, 이들간의 동작 동기화 필요하기도
- 입출력 장치가 진행하던 입출력 완료 -> 입출력 장치는 입출력 완료를 interrupt을 통해서 CPU에게 알림  

### Interrupt
- CPU는 동작하는 중에 입출력 장치로부터 신호를 받음 
- 하던 일을 멈추고 해당 interrupt 처리
- interrupt 벡터 이용 -> interrupt의 유형을 나타내는 고유 숫자(운영체제의 자료구조)
- 운영체제는 interrupt 처리 함수(운영체제 코드 일부)의 시작 주소 가짐
- interrupt 처리함수: 인터럽트 발생시 CPU가 해야하는 일 미리 정의
- interrupt 처리함수 -> 운영체제 코드 일부, 실행된다 : 실행제어가 운영체제로 넘어감
- interrupt 처리함수 종료 -> interrupt 발생하던 시점으로 돌아가야함(interrupt 발생하면 그 시점의 주소 저장)
- 외부 interrupt:입출력 관련 interrupt
- 내부 interrupt (CPU 자체의 동작이 원인이 되어서 발생하는 interrupt): trap, exception
- interrupt driven: interrupt 발생-> 운영체제 동작-> interrupt driven 방식이라고 함 !  

**-> interrupt 발생 지점 저장, 해당 처리 함수 실행, 저장된 지점으로 돌아옴 : 외부, 내부 같은 동작**  

### Interrupt Handling
- interrupt 발생-> 그 상태 보존
  - 인터럽트 발생 시점의 레지스터 값 -> 인터럽트 처리가 끝났을 때 발생 시점의 상황 복원해서 실행 이어감
  - program counter : 수행 지점 저장
- interrupt 구별
  - vector: 장치에서 전달된 벡터를 보고 어느 장치의 인터럽트인지
  - polling: 어느 장치에서 인터럽트를 보냈는지 확인해야 함
- interrupt 처리 함수 실행
  - 발생하는 인터럽트에 따라 운영체제 코드의 각기 다른 부분 실행됨
- Interrupt Timeline
  - 입출력 개시 요청 (실행중인 응용프로그램이 출력 명령, CPU로부터 입출력 장치로부터 입출력 요청)
  - CPU : 입출력 요청 후 자신의 역할 / IO : 해야하는 일 함 -> 병렬 수행 가능
  - 입출력 완료로 인해 interrupt 발생 (입출력 -> CPU)
  - interrupt 보낸 장치 확인, 이에 대한 interrupt 처리함수 실행 : 운영체제 커널 실행
  - 입출력 장치: 다음 지시가 있을 때까지 idle / 처리 끝나고 응용프로그램으로 돌아가 발생직전 하던 일 함

### Storage Structure

- Register: CPU내부의 word 크기/ CPU가 명령 수행할 떄 레지스터 내의 데이터 접근
- cache: CPU 내부 혹은 바로 옆 / CPU가 가장 최근에 접근한 명령이나 데이터의 복사본
- Main memory: CPU가 프로그램 실행하려면 필요, CPU가 접근할 수 있는 유일한 메모리
  - 현재 실행 중인 프로그램 저장
  - 모든 프로그램은 실행되기 위해서 메인메모리에 로드
  - Random access: 데이터가 저장된 위치에 관련없이 속도 일정
  - Volatile: 휘발성
- Secondary Storage(보조저장 장치)
  - magnetic disk: 하드디스크 / 비휘발성 / 원형 금속판+자석물질+데이터 저장
  - ssd: 하드디스크보다 빠름/ 비휘발성/ 플래시 메모리 기반


**-> 위로 갈수록 속도빠름, 단위 용량당 가격이 비쌈, register, cache, main memory까지 휘발성**

- Caching: 저속의 저장장치에 저장되있는 데이터를 일부의 복사본을 빠른 저장장치에 저장, 컴퓨터 동작 성능 향상
  - Main memory -> cache
  - magnetic disk -> Main memory
  - 성능 향상 기법 (컴퓨터 시스템 여러 곳에서 사용)
  - 특정 하드웨어 내, 운영체제 등
  - 주어진 하드웨어 하에서 최대한 빨리 데이터에 접근
  - 고속 저장장치의 용량 < 저속 저장 장치 -> 일부만이 캐싱
  - 가장 최근에 사용한 데이터를 주로 캐싱 -> 가장 최근에 사용한 데이터를 다시 사용할 가능성이 높기 때문
  - 모든 데이터 접근시, 캐쉬 역할을 하는 고속 저장장치부터 뒤져봄 -> 원하는 데이터 캐쉬에 존재할 수도
  - 캐쉬에 있다 -> 성능 향상
  - 캐쉬에 없다 -> 저속 저장장치로 이동 -> 복사한 데이터를 고속 저장 장치에 저장
  - 캐쉬에 저장된 데이터를 새로운 데이터로 갱신: cache replacement


- Device Driver: SSD 이하 -> 입출력 장치  
  - 입출력 전용 프로그램  
  - 이를 통해 운영체제를 저장 장치 특성과 무관하게 일관된 방법으로 데이터에 접근

### I/O Structure 
: CPU 동작 속도, 입출력 장치 속도의 속도 차이 존재하기 때문에 두 가지 방식

- CPU가 입출력 요청, 해당 요청 완료될 때까지 기다림
  - interrupt 발생할 떄까지 idle 명령 반복
  - 한번에 하나의 입출력
- CPU가 입출력 요청 후, 기다리지 않고 자신의 방식 이어감
  - 동시에 여러개의 입출력 병행 가능 -> 내부적으로 device status table를 통해서 각 현재 입출력 상태를 관리함

### DMA - Direct Memory Access Structure
- 일반적 입출력
  - 메모리에 있는 데이터를 프린터 출력 -> CPU가 입출력 명령을 실행해서 한번에 하나씩 입출력 장치로 내보내면서 출력이 이루어짐 (출력할 데이터를 출력장치를 보내는 과정에 CPU 관여)
- 고속 입출력 장치 : DMA 
  - 처음 입출력 개시될때만 CPU 관여, 이후 메모리에 있는 데이터는 관여없이 직접 해당 입출력 장치로 전송되어 출력  
  - CPU 관여없이 Device controller와 main memory간의 블록단위의 데이터 전송
  - Interrupt 발생: 매 바이트마다 발생하지 않고 일련의 블록에 대한 입출력이 완료될 때 한번만 인터럽트 발생

<br>

## 1.3 Computer System Architecture
- Singleprocessor
   - 범용의 명령어 집합을 실행할 수 있는 하나의 CPU
- Multiprocessors (= Parallel systems, multi core system)
  - 두개 이상의 Processor
  - 처리양 증가
  - 비용이 적게 듦
  - 신뢰성 증가 -> 일부 프로세서가 고장나더라도 동작 이어나갈 수 있음
  - Asymmetric Multiprocessing: 한 프로세서가 주 프로세서여서 전체 시스템 제어/ 다른 프로세서들은 주 프로세서의 명령 실행, 미리 지정된 task 시행
  - Symmetric Multiprocessing: 더 일반적/ 각 프로세서가 운영체제와 사용자 프로그램을 동등하게 처리 (SMP) -> 대등
  - UMA: 모든 프로세서에서의 메모리 접근 동일한 시간
  - NUMA: 일부 메모리는 다른 위치에 있는 메모리보다 접근시간 오래 걸림  
  
### multi core
- 하나 칩에 여러개의 core 포함
- 단일 코어를 가진 여러 개의 칩보다 효율적
  - 칩 내의 코어간의 통신이 칩간의 통신보다 빠름
  - 여러 개의 단일 칩보다 전력 소모 적음
  - 두 개의 표준적인 processor를 가지는 multiprocessor 동일하게 보이게 됨
- blade server: 처리기 보드, 입출력 보드, 네트워킹 보드를 하나에 장착
  - 기존 multi processor 와의 차이: 독립적으로 부팅, 자신만의 운영체제 실행  
 

### clustered System

- 둘 이상의 독자적인 computer system이 비교적 고속의 network로 묶여 마치 단일 computer system처럼 동작
- 복수의 시스템
- SAN을 통해 저장 장치 공유
- 고가영성 서비스 제공  
- 고성능 컴퓨팅 환경 제공  
<br>

## 1.4 OS Structure
: 응용프로그램이 실행되는 환경 제공
- Multiprogramming
  - 한번에 여러개의 job(실행되어야하는 응용프로그램)을 메모리에 적재
  - job -> 입출력 요청, 입출력 완료 기다림 -> 다른 job 전환 : CPU 쉬지않고 동작, 매우 효율적으로 사용 가능
  - 새로 시작되는 job 선택 -> job scheduling

- Timesharing (= multitasking)
  - CPU가 다수의 job을 교대로 실행 -> 매우 빈번 : 사용자는 각자 프로그램이 실행되는 동안 상호작용이 가능함
  - interactive computing: 사용자 - 컴퓨터 시스템 관의 직접적인 통신
  - 응답시간 < 1초
  - Process: 동시에 많은 사용자가 컴퓨터 공유, 각 사용자들은 적어도 하나의 프로그램을 가짐 -> 실행중인 프로그램을 process라고 칭함
  - CPU scheduling: 여러개의 프로세스 중에 하나 선택해야함
  - Swapping: 메인 메모리 공간에 문제 될 수도 -> 일부 프로세스를 디스크로 내보냄 -> 추후에 메모리 공간에 여유 생기면 다시 메인메모리로  
  - Virtual memory: 메인 메모리에 프로세스가 있지 않더라도 실행가능하게 해줌

<br>

## 1.5 OS Operations
- Interrupt driven: 실행할 프로세스가 없다면 운영체제는 메모리에 존재하되, 어떤 사건이 일어나기를 기다림 -> 이때 운영체제 동작시킴 -> exception, trap
- exception
  - CPU가 일반적인 명령 실행 중, 오류 처리 루틴 
  - 나눗셈 명령에서 나누는 숫자가 0인 경우 
- trap: 특수 명령
  - 실행결과는 CPU 상태를 interrupt 발생 직후와 같음
  - 인위적으로 interrupt 발생 시킴
  - 응용 프로그램이 운영체제 기능 호출할 때 사용함
- Dual-mode: 최소한 두 개의 분리된 동작 모드를 가짐
  - CPU 특정 레지스터의 비트 값에 의해 결정
  - User mode: 1 -> 특권명령 실행 못함 / 사용자 프로세스 실행
  - Kernel mode: 0 / 운영체제 커널 실행됨
  - 운영체제가 일반 프로세스로부터 운영체제 자신을 보호함

<br>

## 1.6 Process Management
- process: 실행중인 프로그램
- 작업이 단위
- 작업을 수행하기위해 자원 필요(CPU, memory, I/O, files)
- 자원 사용의 주체
- 프로세스 종료 = 자원 회수
- 다수의 프로세스 존재 하나 또는 여러개의 CPU에서 동시에 동작 -> multi flexing
- 프로세스 생성, 삭제 (실행, 종료 책임 됨)
- 잠시 멈췄다가 재개
- 다른 프로세스와 동기화
- 프로세스 간 통신 (IPC) 수단 제공
- 프로세스는 제한된 자원을 가지고 경쟁 -> 교착 상태 발생할 수도

<br>

## 1.7 Memory Management
- 모든 데이터는 처리 전 후에 메인 메모리에 존재
- 모든 명령 또한 실행되기 위해 메인메모리에 적재 되어야 함 -> stored computer
- 메인메모리 관리: 언제 어떤 내용을 메모리에 저장할 것인지 -> CPU활용도 높이고 사용자 응답시간 개선
- 메모리의 어느 영역이 누구에 의해 사용되는지
- 어느 프로세스가 메모리로 가야 하는 지, 나와야 하는 지 
- 메모리 공간 할당, 해제

<br>

## 1.8 Storage Management
- file: 저장 단위 -> 실제 저장장치 용량, 속도 무관하게 데이터 저장하고 사용
- directories fh qnsfl
- 누가 어떤 file에 접근하는 지 제어
- 파일, 디렉토리 생성, 삭제
- 내용 수정
- 특정 위치에 기록
- 의도치 않게 삭제됨 방지 -> backup
- 대용량 저장 장치: disk 장기간 저장 
  - 빈 공간 관리
  - 저장 장치 할당 -> 저장 위치에 따라 접근 걸리는 시간 차이 날 수도
  - 디스크 스케줄링 -> 어떤 순서로 디스크 블록 읽을 것인 지
- 개별 하드웨어 특수성: 사용자로부터 숨김
- 특정 입출력 장치에 이해없이 쉽게 입출력 가능하도록
  - buffering: 입출력 중인 데이터를 임시로 저장 장치에 저장
  - caching: 저속 -> 고속 복사
  - spooling: 입출력 데이터를 직접 장치에 보내는 대신에 메모리에 저장하고 일괄 처리  
  **-> 메모리에 일정 공간 필요한 기법 관리**
  - Divice driver: 일반 프로세스의 입출력 작업을 대행함
  - 범용 디바이스 인터페이스 제공: 특정 하드웨어에 상관없이 일관된 방법으로 입출력 장치 이용

<br>

## 1.9 Protection and Security
- Protection
  - 어느 프로세스, 사용자가 어떤 자원에 접근
  - 다중 사용자: 자원의 소유권, 접근권 -> 정의하는 메카니즘 제공, 감지
- Security
  - 외부, 내부로 부터 보호
- 사용자 구분, 각 사용자가 어떤 일을 하는지
  - 유저아이디 제공, 접근할 수 있는 파일, 프로세스 연관
  - 일련의 사용자를 묶어서 그룹 아이디 만들기도 -> 접근가능한 파일 연관
  - 권한 상등도 가능

<br>

## 1.11 Computing Environments 

### Traditional
- 다수의 독립된 범용 컴퓨터 + 제한 network 환경
- internet 발달 -> 경계 모호해짐
- 네트워킹은 어디에서나 사용, 자신의 컴퓨터를 방어하는 방화벽으로 이루어짐

### Mobile
- 가볍, 이동 가능
- 화면 크기, 메모리 포기
- 802.11 무선 네트워크, 데이터 사용
- IOS, Android

### Distributed
- 분산 시스템: 물리적으로 떨어져 있는 
- 사용자가 시스템 내의 다양한 자원에 접근할 수 있도록 네트워크로 연결
- 공유자원에 대한 접근은 계산 속도,기능, 데이터 가용성, 신뢰성 향상할 수 있음
- 네트워크: 통신 통로, TCP/IP 사용
- 네트워크 컴퓨터 -> 다른 컴퓨터 연결, 통신 가능해도 독자적 / 분산: 덜 독자적, 더 밀접함

### Client-Server
- PC 성능 향상 -> 중앙 집권식 -> PC대처 -> client system에 의해 만들어지는 요청을 처리하기 위한 서버시스템으로 동작 -> 특별한 형태의 분산 시스템: client server
- Compute-server: client가 작업을 요청할 수 있는 인터페이스 제공 -> 이 인터페이스 요청 처리 -> 결과 client에게 (database)
- File-server: client가 파일 생성, 갱신, 읽기, 제거 할 수 있는 인터페이스 제공 (web server)

### peer to peer
- 분산 시스템의 다른 형태
- client- server 구별하지 않음, 모든 노드가 peer
- P2P: node는 peer간 네트워크에 참여
  - node가 네트워크에 잘 알려진 검색 서버에 자신의 server 등록
  - 검색서비스 사용하지 않음, 특정 discobery protocol 통해서 기존 peer network에 참여

### Virtualizaion
- 운영체제가 다른 운영체제 내에서 하나의 응용프로그램으로써 실행
- Emulation
  - Interpretation 기계어가 아니고 고급언어
  - 매우 느림

### Cloud computing
- 계산, 저장, 응용 또한 네트워크 통해서
- 가상의 논리적 확장
- 인터넷 상에서 누구나 사용
  - public cloud: 서비스 지불 가능한 누구나
  - private cloud: 회사내에서 사용
  - Hybrid cloud: public+private
  - SaaS (Software as a Service): 인터넷을 통해 사용가능한 applications
  - Paas (Platform as a Service): 인터넷을 통해 제공되는 software stack
  - IaaS (Infrastructure as a Service): 인터넷을 통해 사용가능한 서버, 저장 장치

### Real-Time Embedded
- 로봇, 자동차엔진, 가전 제품
- 제한된 기능만 제공
- 내장형
- 유닉스 범용 운영체제 -> 특수목적 응용프로그램 실행
- 실시간 응용 체제 -> 정의된 시간내에 작업 처리 이루어져야 함

<br>

## 2.1 OS services
- OS는 응용 프로그램이 실행 할수있는 환경과 각종 서비스 제공함
- User interface
  - CLI(command-line), GUI(graphics user interface)
- Program execution
  - 운영체제는 보조기억장치에 있는 프로그램을 메인메모리로 적재 -> 프로그램 실행 시작되도록
  - 정상 종료, 비정상 종료도 책임
- I/O operations
  - 응용프로그램은 모두 입출력함
  - 자신이 필요한 입출력을 운영체제가 대행함
  - 응용프로그램이 입출력 필요 -> 운영체제에게 요청 -> 운영체제가 실제 입출력
- File-system manipulation
  - file, directory 읽고 저장, 접근권한 
- Communications
  - 프로세스간 통신(프로세스간 정보 교환) -> 같은 시스템 내, 네트워크로 연결
  - IPC / networking
  - 공유 메모리 사용/ 메시지 사용
- Error detection
  - 컴퓨터 동작 중 발생하는 오류에 대해 지속적으로 모니터링
  - 자원의 오류
  - 실행 중 프로그램의 논리적 오류
  - 전체적으로 동작을 이어나갈 수 있도록
  - debugging -> 발생원인잡음: 시스템을 효율적이고 안전하게 사용할 수 있도록
- Resource allocation
  - file, lock 도 포함
  - 프로그램 실행에 필요한 모든 자원
  - 자원에 대한 관리
  - 효율성, 공정성 기반
- Accounting
  - 어느 사용자가 어떤 종류의 자원을 얼마나 사용할 것인지 기록해둠
- Protection and security 
  - protection: 여러 프로세스가 다른 프로세스가 방해해서는 안됨, 모든 시스템 자원에 대한 접근 통제
  - security: 외부로부터 불법적 접근 차단

<br>

## 2.2 User Operating System Interface
### CLI
- 키보드 입력을 명령
- command line
- 화면 + 키보드
- kernel 내 구현, 별도의 응용프로그램
- 웅용프로그램 - shells (shell마다 부가적인 기능)
- 명령: command interpreter가 처리 혹은 실행
- 하고자 하는 작업에 더 빨리 접근

### GUI
- 사용자가 직접 명령 하지 않고 desktop interface
- 마우스, 키보드, 모니터, 터치스크린
- 시스템 일부만 사용

<br>

## 2.3 System calls
- 운영체제가 제공하는 서비스를 사용할 수 있도록 하는 프로그래밍 인터페이스
- 함수 형태로 제공 -> 응용 프로그램이 직접 호출
- API 작성을 더 자주 사용 
  - 같은 API를 사용하는 다른 system에서도 잘 compile
  - 호환성이 좋음
  - system call은 자세한 명제가 필요하고 사용이 어려움
1) 응용프로그램이 system call 호출
2) 호출로 system call interface를 통해 kernel mode
3) 해당 system call 번호로 처리함수 시작 주소 찾음
4) 커널 내에서 처리함수로 제어가 옮겨져서 처리함수 시작
5) 처리 함수 실행 종료 후 복귀
6) 실행 모드 커널-> 유저, 응용프로그램의 system call 호출 직전으로 

### System call parameter passing
1) parameter가 CPU register
2) memory에 저장, 시작 주소를 레지스터에 담아 parameter로 전달
3) stack에 push 해 전달
*2),3) -> parameter 갯수에 제약 없음*

<br>

## 2.4 Types of System Calls

### Process control
- prcess: 언젠간 종료, kernel: 계속 실행
- 새 프로그램 실행 -> 메인 메모리로 적재해야 함
- process 특성을 가져올 수 있고, process 속성을 변경할 수도 있음
- system call을 통해 지연 가능 (원하는 시간, 특정 사건 발생) 
- 메모리 할당 요청, 메모리 반납
- process 실행 중에서 예기치 않은 오류 -> 프로세스 중단 -> 메모리 상 파일로 디스크 저장: dump memory
- Debugger
  - 대상 프로그램 실행, 사전 지정 위치 실행 멈춤: break point
  - 디버깅하는 프로그램을 한 명령/ 한 라인 단위로 실행: single step
- lock: 공유 데이터를 잠글 수 있음 -> 독점 접근
- MS-DOS
  - 단일 tasking -> 하나의 프로그램을 실행하기 위해 간단한 방법, 새로운 프로세스를 추가로 생성하지 않음
  - 프로그램 적재할 때 command interpreter는 자신의 일부를 실행하는 프로그램으로 덮어씀
  - 종료, 오류 발생하면 trap 발생
  - 어느 경우든 오류 코드 저장
  - command interperter 덮어쓰이지 않은 부분이 실행 재개
  - 덮어씌었던 자신의 일부분을 다시 디스크로부터 다시 적재
  - 앞서 놓은 코드 사용자나 다른 프로그램이 사용할 수 있도록 함
- FreeBSD
  - Multitasking: interpreter는 다른 프로그램이 실행되더라도 자신의 실행 계속
  - shell: fork(): 새로운 프로세스 생성 -> exec(): 새프로세스 상에서 새로운 프로그램 실행될 수 있도록
  - shell - 종료하기를 기다림, 자신의 실행 이어나갈 수도
  - 후자 - 사용자는 shell을 통해 다른 프로그램이 실행되도록 요청, 기존 실행중인 프로그램 감시 -> 우선순위 변경
  - exits - 적절한 상태 코드가 명령해석기로 전달

### File management
- 커널은 파일 생성, 삭제
- 파일 오픈, 읽고 쓰고 접근 위치 바꿈
- 사용 끝나면 close system call
- 속성얻거나 수정
- directory folder 일련의 연산도 system call
- system call을 통해 동일한 작업을 실행하는 API/ system program 제공 (후자의 경우 다른 프로그램에 의해 호출가능하면 API로 볼 수도)

### Device managemnet
- 동작하는 과정 중에서 추가 자원 필요
- 추가의 자원: main memory, disk drive, file
- 해당 자원이 가용하다면 프로세스에게 주어지고 프로세스는 실행이어감
- 자원이 사용가능할 때까지 프로세스가 기다려야함
- 요청에 의해 할당 받은 자원은 반납 -> system call
- 파일 open close와 유사
- 장치의 속성 얻거나 변경
- 해당 장치를 장착, 분리

### Information maintenance
- 날짜, 시간 돌려줌/ 변경도 가능
- 현재 사용자수, 운영체제 버전, free memory
- time profile: 프로그램이 특정 메모리 위치에서 실행한 시간 양 

### Communications
- message passing model
  - 직접 교환, 메일 박스
  - 통신 이루어지기 전에, 연결이 이루어져야함
  - 연결 끊어짐
- shared memory model
  - 한 프로세스가 다른 프로세스가 소유한 메모리에 접근
  - 공유메모리 생성
  - 생성된 공유메모리를 자신의 일부로 포함

### Protection
- networking, internet -> 서버에서 휴대용 모두 고려하게 됨
- 권한 얻음, 권한 설정
- 파일, 디스크 허가
- 특정 사용자가 지정된 자원에 대한 접근 권한을 허락, 거절

<br>

## System programs
- 사용자, 관리자, 개발자들이 프로그램 실행하는데 편리한 환경 제공
- File management
  - 탐색기
  - 파일 삭제, 생성, 복사 등
- Status information
  - 시스템 각종 상태 정보
  - 시스템 날짜, 시간, 디스크 양, 디버깅
  - 환경 설정 정보 저장, 검색 -> registry
- File modification
  - text editors: 파일 생성, 수정
  - Unix 운영체제 잘 갖춤
- Programming- language support
  - 컴파일러, 어셈블러 등 위해
- Program loading and execution
  - 잘 사용되진 않지만 Absolute loaders, relocatable loaders 등
  - debugger
- Communications 
  - process, 사용자 접속을 생성
  - 한 사용자 -> 다른 사용자 원격 접속, email
  - client 역할

### Background Services
- 정해진 기능 수행 후 종료
- 시스템이 셧다운 될때 까지 계속 수행
- 시스템의 시작과 함께 실행을 시작해서 늘 동작 -> daemons
  - 외부에서 들어오는 network 연결 요청을 듣고 있다가 요청이 들어오면 적절한 service program에 전달
  - 미리 정해진 일정에 따라 프로그램 실행 시키는 scheduler daemons
  - 오류 발생했을 때 logging하는 daemons

### Application programs
- 일반적인 문제 해결
- 워드 프로세스 -> 운영체제 일부는 아님
- 일반 사용자에 의해 command line, mouse click 등으로 실행됨

<br>

## 2.6 OS Design and Implementation
- system 설계/ 구현 -> 문제점
- 어떤 하드웨어 사용? 어떤 유형의 시스템? (timesharing, single user, real time system)
- user goal
  - 사용자 입장
  - 운영체제 사용하기 쉽고,편리 , 안전, 신속, 배우기 쉬움
- system goal
  - 설계, 구현, 유지보수 쉬움, 신뢰성, 효율성, 오류없이 동작
- mechanism(어떤 일을 어떻게) 으로부터 policy(무엇을 할 것인지) 분리 -> flexablity 부여
- policy -> 시간에 따라 바뀔 수 있음, mechanism이 영향 받을 수도
- 잘 분리 -> 매게 변수만 조정, 정책 변경을 반영할 수 있음

### Implematation
- C, C++
- 어셈블리 언어
- 고급언어로 만들 수록 이식 쉬움
- 운영체제 하드웨어에서 바로 동작하지만 개발에서는 직접 테스트하지 않고 에뮬레이션 사용

<br>

## 2.7 OS Structure
- OS 큼 -> 잘 동작, 쉽게 수정
- MS-DOS
  - kernel: MS-DOs device drivers, resident system program
  - ROM BIOS device drivers 에 의존함
  - 경우에 따라 응용프로그램이 직접 ROM에 접근
  - 내부적으로 잘 분리되어 있지 않고 인터페이스도 정의되어 있지 않음
- UNIX
  - 각종 시스템 프로그램/ 커널
  - 시스템 프로그램: 컴파일러 + CLI + interpreters + system libraries
  - 커널: system-call interface(응용프로그램으로부터 system call 받음) + file system, CPU scheduling 메모리 관리 + kernel interface (하드웨어와 직접적)
  - 커널에 하나에 담겨있고 계층적 구조는 아님
  *-> 성능상으로 좋을 수도, 모두 커널에 있어서 유지보수 힘듦*
  
### Layered-Approach
- Layer 0 : hardware
- Layer N : 사용자 인터페이스
- 커널 -> 둘 사이의 어딘가 (1 ~ N-1)
- UNIX: 계층으로 보이지만 커널이 하나
- 커널도 여러개의 계층이 되어야 함 -> 각 계층은 데이터와 이를 조작하는 연산으로 구성된 추상화된 객체
- 자신과 하위 계층의 함수 호출 가능 -> 이전 계층까지는 올바르게 작동 검증 -> 오류는 해당 계층에 존재
- 구현, 디버깅 간단
- 하위 계층이 어떻게 구현되었는지 알 필요없고 어떤 연산을 하는지만 
- 설계시에 여러층 적절히 배분해서 정의
- 각 층은 하위 계층만 사용 -> 신중하게 설계
- 하나의 동작이루어지는데 여러 계층 -> 다른 구조보다 효율성 떨어짐

### Microkernel System Structure
- 커널에 있었던 일부 구성요소들을 커널로 떼어내서 사용자 수준 프로그램으로 구현, 커널은 아주 기본적인 기능만
- 프로세스 관리, 메모리 관리, 프로세스 간 통신
- 파일 시스템 -> 사용자 수준 프로세스
- client - service 통신 (메시지 전달)
- 운영체제 확장 쉬움 (새로 추가되는 것은 사용자 수준, 커널 변경 필요없음)
- 다른 하드웨어로 이식이 쉬움
- 커널이 아닌 사용자 프로세스에 의해 실행 -> 보안/신뢰성 향성 (커널이 작기때문에 작은 코드에서는 큰 프로그램에 비해 오류가 발생할 가능성 낮음)
- 성능 떨어짐 -> 한 번의 커널로 이루어질 수 있는데 microkernel은 수차례의 메시지 전달 과정 거침 -> system call 훨씬 빈번함

### Kernel modules
- 프로그램 실행 단위 (function)과 다름
- 커널 핵심 기능 이외, 시스템 동작 중에 동적으로 추가될 수 있는 서비스 구현
- kernel module로 구현하면 부팅 시점부터 커널에 포함되지 않고 시스템 동작이 이루어지는 도중에 해당 파일 시스템이 필요해지는 경우 이미 실행중인 커널에 동적으로 연결
- 언제든지 커널에 붙였다가 떼어낼 수 있음
- 커널 전체를 빌드할 필요없이 커널 모듈만 컴파일
- 부가 기능들을 커널 모듈 -> 커널을 작은 규모로 이해가능
- 최근 대부분 운영체제 

### Hybrid Systems
- 성능 보안 편의성 -> 여러 방식 혼합
- MAC OS
- IOS
  - cocoa touch: 모바일 장치상에서 개발되는 Objective C API
  - Media services: audio, video
  - Core services: databases
  - Core OS: Mac OS X kernel
- Android
  - Open 소스
  - 리눅스 커널 + power management
  - web browser, database, multimedia, 표준 C 라이브러리

<br>

## 2.10 System Boot
- 전원 -> CPU는 메모리 특정 주소(ROM 영역)로부터 명령 가져옴
- intel cpu: ffff0 번지의 명령부터 실행 시작
- OS 는 하드웨어가 자신에게 접근해서 시작하도록
  - ROM 영역에 bootstrap loader 존재 -> 운영체제 커널의 위치를 찾아서 메모리에 로드하고 시작 : 매번 동일한 위치의 동일한 운영체제만 부팅 가능
  - ROM 코드가 디스크 등의 저장 장치의 앞부분에서 boot block 읽어서 실행시키고 boot block에서 운영체제 로드 : 다른 운영체제 설치하더라도 해당 운영체제에 맞는 부팅 방법
- bootstrap loader: GRUB -> 다양한 디스크 파티션으로부터 여러 종류의 여러 버전의 커널 쉽게 선택해서 부팅
- kernel load된 후 시스템 동작함

<br>

## Multiprogramming
- 이후 모두 multiprogramming 이라고 가정
- 운영체제가 프로그램을 실행시키는 방식에 관한 것
- 프로그램 시작 -> 메모리에 적재된 것으로 시작
- Uni-programming 에서 CPU를 idle 상태로 놔두는 것은 낭비 -> 낭비 줄이고자 함
- Throughput/ Utilization / Degree of multiprogramming(동시에 실행되는 프로그램 증가)
- 최대한 많은 수의 프로그램을 동시에 실행하는 게 좋음

<br>

## 3.1 Process Concept
- job = process
- 실행 중인 프로그램 = process
- text
  - 프로그램 명령 - 코드
  - program counter를 포함한 CPU register
- stack
  - 프로그램 실행 도중 함수 관련
  - parameter, 복귀 주소, 함수 지역 변수
  - 함수 호출 될 때마다 push, 종료될 때 pop
- data
  - 전역 변수
- heap
  - 프로세스 실행 도중 동적 메모리 할당
- stack, heap 사이 -> 프로세스
  - 아직 사용되지 않은 빈 공간
  - stack(함수 중첩), heap(동적 할당) -> 늘어날 수 있음 / 줄어들 수도
  - 나머지 부분은 크기 변화 없음
- program
  - passive (executable file)
- process
  - 다음에 실행할 CPU명령을 지정하는 computer register/ 실행에 필요한 자원 집합 가짐 -> 능동적
  - 실행 file의 형태 프로그램이 메모리에 적재되어야 process 가 됨
- program은 하나지만 여러개의 process 존제
  - text 동일/ data, stack, heap 내용 다름

### Process State
- Process State
  - new: process가 만들어지고 있는 과정 -> 정식 process는 아님
  - ready: CPU에 할당되기를 기다림 / CPU가 주어지면 즉시 실행가능
  - running: 프로세스가 실행 중 
  - waiting: process가 어떤 사건을 발생하기를 기다림 (사건: 입출력 완료)
  - terminated: 프로세스 실행 종료 -> 소멸되지는 않은 상태
- Diagram of process state
  - new -> terminated 까지 상태 전이
  - new -> ready (admitted): 정식으로 프로세스가 될 대상 선택
  - ready -> running (scheduler dispatch): CPU 할당
  - running -> ready (time interrupt): 실행중인 프로세스가 너무 장시간 독점
  - running -> waiting: 입출력 요청
  - waiting -> ready: 입출력 완료 interrupt (모든 입출력에서 발생)
  - running -> terminated: 실행중인 프로세스가 종료 system call 호출

### PCB - Process Control Block
- 커널이 개별 프로세스를 관리하기 위한 자료구조
- 한 프로세스에 해당하는 모든 정보가 담겨있음
- PCB - 프로세스마다 하나/ 프로세스 생성, 소멸할 때 같이
- linked list로 관리
- Process state
  - ready, running 등
- Process ID
  - process 구별
  - 정수로 표기
- program counter
  - CPU 안에 있는 register 현재 실행 중인 명령 담음 (복사본)
  - running 아닌 경우만 의미 있음
  - 최근 실행 멈춘 곳에 주소 담김 -> 실행 재개 할때 이 field 저장 주소
- CPU registers
  - PC 제외 register
  - 가장 최근에 실행을 멈춘 시점의 정보
  - 재개하면 이 field의 값이 restore
- CPU scheduling
  - CPU 할당할 process 선택
  - 우선순위, 스케줄링 큐
- Memory-management information
  - process가 할당받아서 사용하고 있는 Mainmemory 위치 정보
- Accounting information
  - process 자원 사용 기록
  - CPU 사용 시간, 자원 한도
- I/O status information
  - process에 할당된 입출력 장치, open file
- current
  - 현재 실행중인 Process의 PCB 가리키는 포인터 
 
*p0 -> p1 전환할 때, 전환하는 과정에서 p0의 상태를 p0의 PCB에 저장*  
*p0 -> kernel -> p1 -> kernel -> p0*

<br>

## 3.2 Process Scheduling
- multi programming: CPU 이용 극대화
- Timesharing: 프로그램이 실행되는 동안에 사용자와 상호작용하도록 CPU 빈번하게 교체
- Process scheduler: 실행가능한 여러개의 프로세스 중 하나의 프로세스를 선택함
- scheduling queues: 스케줄러의 선택 대상이 되는 프로세스를 모아놓음
  - Ready queue: 메모리에 있으면서 즉시 실행가능한 process (ready)
  - Device queue: 장치마다 하나, 입출력 개시/ 완료 기다림 (waiting)
  - Job queue: 시스템에 있는 모든 process 포함 -> 전체 process 목록 

**-> process는 자신의 lifecycle동안에 이런저런 scheduling queue를 옮겨 다님**  

### Schedulers
- Short-term scheduler( = CPU scheduler)
  - 다음 실행할 프로세스 선택, 이 프로세스한테 CPU 할당
  - ready 중에서 고름
  - ready queue
  - 작은 규모에서 유일한 scheduler
  - 매우 빈번
- Long-term scheduler (= job scheduler)
  - 어느 프로세스를 ready queue로 가져올건지
  - new -> ready
  - degree of multiprogramming(동시에 실행 중인 프로그램) 조절 -> 추가
  - I/O bound / CPU bound 적절히
- Medium-term scheduler
  - swap 영역으로 내쫓음
  - main memory의 빈공간 확보를 위해서
  - prcess 수가 줄어들면 다시 main memory에서 가져옴 
  - degree of multi programming 조절 -> 감소시키는 건 아님
  - 종료하면서 자연스럽게 종료
  - 시스템이 일시적으로 높을 때 동작
  - waiting 상태인 애 쫓음
- I/O bound process
  - 입출력 위주로
  - 짧은 CPU bursts
- CPU bound process
  - 계산 위주
  - 긴 CPU bursts 
*I/O bound process 너무 많음 -> ready queue 빔 -> CPU scheduler 가 할 일 없어짐*    
*CPU bound process 너무 많음 -> 각종 입출력 장치의 IO queue 가 빔 -> 입출력 장치가 할 일 없어짐*

### Context Switch
- 이젠 프로세스 상태 저장, 다음 프로세스 복원
- Context: 사용중인 CPU 레지스터가 프로세스의 main memory 상의 내용을 통칭
- PCB에 기록됨
- Context - switch : 다른 프로세스로 옮겨감
- 시간 -> 그 자체로 시스템의 overhead
- 하지 않을 수는 없는데 의미있는 작업은 아니여서 overhead -> 신속 짧게
- 신속하게 할 수 있는 하드웨어 있음 -> 명령하나로 store, restore

## 3.3 Operations on Process
- kernel이 process에 제공하는 연산 (system call)
- 생성: 새로운 프로세스 만듦
  - 기존: parent
  - 새로: child
  - process tree 생성 -> 한 프로세스가 여러 프로세스 만들수도 / init(root) -> kernel 부팅 과정 직접 만듦 (복제해서 만드는게 아니라)
  - pid로 식별
  - 자원의 공유: 부모, 자식 모두/ 일부/ 전혀 공유하지 않을 수도 -> 물려주는 경우가 많음
  - open 상태 -> 자식에게도 open file 물려줌
  - 메모리 주소공간: 그대로 복제 -> 부모 프로세스와 내용 같음
  - 자식 생성: fork() -> 이를 호출한 process의 주소 공간을 system call에서 지정한 실행 파일로 교체 (부모의 것과 다른 자신의 프로그램 실행):exec() -> exit(): 종료 (부모는 wait 중)
  - fork(): system call 시작할때는 하나, 정상 복귀할 때 process가 두 개가 됨/ parent, child에게 return -> fork가 return 시점부터 자식 생성
  - fork의 리턴값은 달라짐
  - execlp: ls 라는 프로그램이 대체 / process는 유지 / 실행 코드만 바뀜
- 종료
  - exit() -> 하나의 정수 parameter = exit status -> 부모에게 전달 가능
  - 운영체제에 의해 모든 자원 회수
  - 정상 종료
  - abort() -> 자식 프로세스를 강제종료하기 위해 부모가 사용
  - 자원 사용량 지나치게 많을때/ 자식이 수행하는 작업 필요없음/ 부모 프로세스가 exit 먼저해서 자식 Process만 남은거 허용하지 않을때
  - cascading termination : 부모 종료했는데 자식이 남아있을때 사용
- 대기
  - exit() 하면 terminated 상태 -> 완전 소멸된 상태아님
  - 부모가 wait() 리턴하면 완전히 소멸
  - wait: 자식 함수의 종료를 기다림
  - pid = wait(&status);
  - 자식 프로세스의 종료상태 전달도 할 수 있음
  - 자식의 종료/ 부모의 기다림 시간 엇갈릴 수도
  - 1) 자식 프로세스가 exit 했는데 부모 wait 하지 않음 -> 자식은 zombie
  - 2) 부모 프로세스 먼저 종료 / 자식 프로세스는 아직 동작-> Orpha

<br>

## 3.4 Interprocess Communication (ITC)
- independent: 한 프로세스가 다른 프로세스 실행에 영향 끼치지 않음
- cooperating: 다른 프로세스 실행에 영향 주고 받음 
  - 정보 공유, 작업 나눔, 모듈화된 시스템을 여러 프로세스, 한 사용자가 편리함 목적으로 여러 프로세스 사용
  - 데이터 정보 교환 -> ITC 필요
- Shared memory
  - 일정한 메모리 영역 공유
  - 각 프로세스 입장에서 자신의 메모리 일부로 간주 -> 읽거나 쓸때 문제 없음
  - 별도의 송신 수신 함수 필요없음
  - 운영체제가 아닌 사용자 기반 프로세스의 통제하에 이루어짐
  - A 쓴 후 B가 읽음 -> 먼저 이루어진다는 보장은 없음
  - 사용자 프로세스가 통신하기 위해 접근할 때 동기화에 필요함 
- message passing
  - process 간 통신, 동기화를 위한 IPC 메커니즘 중 하나
  - message queue: 송신, 수신할 때 이용/ kernel 내 존재
  - 커널이 send, receive 제공
  - 메시지 크기 -> 고정 크기 or 가변 크기

### Naming
- Direct communication
  - 통신을 원하는 각 프로세스가 송,수신자의 이름 명시해야함
  - send(P,message): P에게 송신한다, receive(Q,message): Q로부터 수신한다
  - 자동으로 연결, 매번 상대방 지정함
  - 연결은 오직 두 Process 간
  - 단방향, 양방향 가능

- Indirect communication
  - mail box이용 (= port)
  - 특정한 아이디의 메일박스 공유해야함
  - 공통의 mail box 간에만 Link 연결
  - 하나의 mail box에 여러 process 접근 가능
  - 한 쌍의 process 간의 여러개의 link 공유 가능
  - mail box 생성/ mailbox로부터 송수신, 메일박스 제거
  - send(A,message): A(송신하고자 하는 mailbox)
  - receive(A,message): A(수신하고자 하는 mailbox)
  - 3개의 프로세스 -> A공유 / p1 -> p2,p3 누가 실제로 수신함 ?
  - 하나의 메일박스에 최대 두 프로세스 -> p1과 연관된 Process에 따라 수신자 결정
  - 한 번에 한 프로세스만 receive 연산 -> p2, p3 중 먼저 receive 성공
  - system이 선택할 수도 -> p1에게 통보하는 방식

### Synchronization
- Blocking (= synchronous)
  - Blocking send: 보낸 메시지를 receiver가 수신할 때 까지 send block됨 -> 송신될 때까지 waiting 상태
  - Blocking receive: 수신자가 receive 연산해서 받겠다고 했는데 도착하지 않은 경우 -> 도착할 때까지 수신자가 waiting 상태  
  
*-> rendezvous*
- Non- blocking (= asynchronous)
  - Non-blocking send: 수신자가 수신했는지 상관없이 할 일함
  - Non-blocking receive: 메시지가 도착하지 않았을 때 null 메시지 받음 

### Buffering
- 송신 측에서 송신 시작 직 후, 수신 완료될 때까지 이동하는 메시지가 저장되어야 함
- 송신 중인 메시지를 임시 저장
- direct/ indirect -> queue 존재
- 1. zero capacity
  - 대기 하는 메시지 존재하지 않고 sender는 reciever가 수신할 때까지 기다림 -> blocking 방식
  - reciever: sender가 보내면 즉시 받아야 함
  - 버퍼링 필요없음
- 2. Bounded capacity
  - 버퍼링 공간 제한
  - queue가 가득 차있음 -> queue가 비워지기를 기다림
- 3. Unbounded capacity
  - queue 길이 제약 없음
  - 기다리는 일 없음












