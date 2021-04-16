## 4.1 Threads
- code, data files -> 공유가능, 하나만 존재
- registers, stack -> 분리됨
- 실행시마다 달라지는 부분 -> thread (register+stack)
- multithreaded
- 실행의 기본 단위: thread / 스케줄링 대상 
- 하나의 응용, 여러 개의 실행 흐름을 가진 독립적인 process로 구현됨
- 하나의 응용이 수행해야하는 여러 개의 기능 -> 개별 thread로 구현
- word processor 
  - graphic 표시 thread
  - 키보드 입력 thread
  - back ground에서 스펠링 체크 thread
- process: 무거운 단위 / thread: light-weight
- 코드 구성 단순화, 효율 증가
- kernel 자체가 여러 개의 thread로 이루어짐

### Multithreaded Server Architecture
- client -> server: request
- 사용자 요청 대기하고 있던 thread가 받아서 이 요청을 서비스할 수 있는 서비스 스레드 생성 처리 넘김
- 서버는 또 다른 client 요청 대기
- 하나의 web server process가 요청 받을 때마다 하나의 요청 받는 thread와 다수의 client를 담당하는 thread들로 이루어짐
- Responsiveness
  - 대화형의 응용프로그램의 경우, 응용프로그램의 일부가 block, 긴작업하더라도 프로그램의 다른 부분은 수행이 계속되므로 사용자에 대한 응답성 향상
- Resource Sharing
  - Process: 공유메모리, message passing 이용 -> 명시적으로 공유를 표시해야함
  - Thread: 자신이 속한 process내의 다른 thread와 메모리, 자원 공유 가능
- Economy
  - 메모리, 자원 할당 -> 저렴한 비용
- Scalability
  - single threaded: CPU가 여러개이더라도 오로지 하나의 처리기에서 실행
  - multithreaded: 각 thread가 다른 처리기에서도 병렬적으로 처리가능
 
 <br>
 
## 4.2 Multicore Programming
- SMP: 여러개의 CPU 가능
- 단일 CPU 칩안에 computing core가 여러개
- 각 core가 독립적인 CPU로 보임
- Parallelism (병렬 수행)
  - 여러개의 task 동시 실행 가능
  - Data paralleism: 하나의 데이터 부분 집합을 다수의 computing core에 분배 -> 각 코어가 동일한 연산
  - Task paralleism: thread가 동일한 작업을 하는게 아니라 다른 작업 -> 다수의 코어에 분배
  - Dividing activities: 독립된 병행 가능한 task로 나눌 수 있는 영역 찾음 
  - Balance: 각 부분이 전체 작업에 균등한 기여도
  - Data splitting: task가 접근하는 데이터가 개별 코어가 사용할 수 있도록 
  - Data dependency: 둘 이상의 task 사이에서 종속성 검토
  - Testing and debugging: 다중코어 -> 병렬 실행 -> 다양한 실행 경로 -> 디버깅 주의
- Concurrency
  - CPU 하나여도 가능
  - multi programming/ time sharing

### Amdahl's Law
- 순차 실행, 병렬 실행 동시에 가지고 있는 응용에 대해서 코어 갯수를 늘렸을 때 얻을 수 있는 성능향상
- S: 순차 실행 비율, N: core 갯수
- 1 / (S + 1-S / N)
- N 커지면 성능 향상 , 1/S 수렴
- 현재의 멀티 코어 시스템을 설계하는데 사용하는 하드웨어 성능 개선 기법들을 고려하지 않았음
- 코어 개수가 증가할수록 적용 힘들 것이라고 주장

<br>

## 4.3 Multithreading models
- User threads
  - 사용자 수준에서 사용
  - 사용자 수준 -> thread library사용
  - 하위에 있는 운영체제 kernel에서 thread 기능을 제공하는지와는 무관
  - POSIX Pthread/ Windows thread/ JAVA threads
- Kernel threads
  - 오늘날의 범용 운영체제들은 거의 지원

- 연관관계 존재
  - user thread 만들어서 사용해도 user level에 대응하는 kernel thread 만들어져야 함

### Multithreading models
- Many to One
  - 여러 개의 user thread가 하나의 kernel thread에 연관
  - 다수의 user thread 중 하나가 blocking system call(waiting 상태, 입출력) -> kernel blocked -> 나머지도 blocked
  - 한 번에 하나의 thread만이 kernel에 접근, 여러 thread가 다중 core system에서 병렬로 실행될 수 없음
  - 사실상 한 번에 한 thread 수행됨
  - 원하는 만큼의 user thread 생성 허용하기는 함
- One to One
  - 각 user thread와 kernel thread가 1:1
  - 많은 concurrency 제공
  - 여러 thread를 병렬로 수행할 수 있음
  - 너무 많은 kernel thread가 생성될 수도 -> 갯수 제한 함
  - windows, Linux
- Many to Many
  - 여러개 user thread - 여러개 kernel thread (user thread >= kernel thread)
  - 어느 thread가 연관될 지는 그때마다 달라짐'
  - 1-1 kernel 너무 많아지는 것 보완
  - M-M과 달리 blocking call 가능
- Two-level model
  - M:M + 1:1
  - 기본적 -> M:M/ 일부 -> 1:1

<br>

## 4.4 Thread Libraries
- 프로그래머에게 thread 생성, 관리하는 API 제공
- 1. user level에서만 구현
- 2. kernel 수준의 library

### Pthreads
- user level, kernel level
- POSIX(운영체제 표준)의 일부
- 1003.1c 준수
- specification(어떠해야 한다)만 언급, 어떻게 구현해야하는 지 언급 안함 -> 각각의 개발에 따라 다를 수도
- UNIX 지원

<br>

## 4.5 Implicit Threading
- 하나의 프로그램을 구성하는 thread 많아짐
- 올바르게 동작하는 지 검증하기 어려워짐
- compiler가 thread 생성, 관리
- Thread Pools
- OpenMP
- Grand Central Dispatch

### Thread Pools
- thread 갯수가 무한으로 늘어나는 것을 막음
- process 시작할 때 미리 일정한 수의 thread를 pool로 정의
- 요청 발생하면 pool에서 thread 할당
- 미리 thread 만들어줌 -> 필요시 새로 만드는 것보다 빠름
- 한 응용을 위한 thread 갯수를 pool의 크기로 제한
- task 실행, 생성을 분리 -> 다양한 전략에 적용 (task 일정한 시간 후 실행, 주기적 실행)
- Windows API 지원

### OpenMP
- C, C++, FORTRAN
- 공유 메모리 환경의 병렬 지원
- 코어 개수만큼 thread 생성 -> 생성된 thread는 동시에 실행
- 각 thread가 parallel 빠져나오면 종료

### Grand Central Dispatch
- Apple -> Mac OS / C, C++, API
- block 정의 "^{ }"
- block 을 dispatch queue에 넣어서 실행
- 이 큐에서 block에서 하나 꺼내서 thread pool에서 꺼낸 가용 thread와 매칭해서 thread가 내용 실행
- 병렬 section 확인
- 자신이 원하는 병렬수행되고자 하는 부분은 block 으로 표기
- dispath queue
  - serial -> FIFO: queue에서 꺼내진 내용은 다른 block이 꺼내지기 전에 수행/ process 마다 각각 하나씩 가지고 있음 -> main queue
  - concurrent -> FIFO, 한번에 다 꺼낼 수 있음/ 이들을 동시에 실행 가능 (3개 -> low, default, high)

### Threading Issues
- fork(), exec()의 의미 달라짐
  - 한 process thread 두 개 -> 복사된 자식 process도 thread 두 개?
- Signal handling
  - process에게 가는 신호/ thread 여러개 -> 어느 thread가 받아야 함?
- Thread cancellation
  - 종료 시키는 문제
- Thread local storage
- Scheduler activations 

<br>

## 5.1 Background
-  process 들이 병행 수행을 함
-  multi programming -> CPU 수행 높임 (time sharing, multitasking -> 시간 제한)
-  개별 프로세스 입장: 코드 실행 도중 언제든지 중단되어서 다른 process로 전환
-  다수의 process간 데이터 공유, 공유 데이터를 접근하는 process 중 하나가 공유 데이터 접근 도중에 실행 중단 -> data consistency 깨짐
-  Producer-consumer problem
  - 공유 변수를 접근하는 독립적인 process가 consistency를 깰 수도 있음
  
### Race Condition
- 공유 data의 consistency 깰 수도 있는 경우
- 병행 process: producer, consumer / 공유 데이터: counter
- counter++
  - register1 = counter
  - register1 = register1 + 1
  - counter = register1
- counter--
  - register2 = counter
  - register2 = register2 - 1
  - counter = register2
  
**-> 각각 3단계가 끝난 후에 다른 process가 접근 해야함**

<br>

## 5.2 Critical Section Problem
- 임계지역문제
- critical section: 공유 데이터 접근하는 부분 (counter 증가, 감소하는 부분)
- 하나의 process가 critical section에 접근하는 동안, 다른 process는 critical section에 들어가면 안됨
  *-> 한번에 한 process만이 critical section에 들어갈 수 있다*

- entry section: critical section 실행 직전
- remainder section: 공유데이터 접근과 관련 없는 부분
- exit section: critical section 직후

### Critical Section
```
do {
  entry section
    critical section
  exit section
    remainder section
} while (true);
// entry, exit -> problem solution
```
- Mutual Exclusion
  - 상호배제 조건
  - Pi가 자신의 critical section 실행하는 도중, 다른 Process는 critical section에 접근할 수 없음
- Progress
  - entry section에 있는 process만 / remainder section에 있는 progress는 critical section 접근 관련 영향을 미칠 수 없다
  - critical section을 실행하는 process가 현재 없으며 일부 Process들이 자신의 critical section에 진입하려고 하면 이들만이 다음번 critical section에 들어갈 수 있다
  - 또한 이것은 무한정 연기될 수 없다
- Bounded waiting
  - progress가 자신의 critical section에 진입하려고 시도한 이후부터 그 시도가 이루어질때까지 다른 process가 critical section에 진입하려고 하는 횟수에 제한 둠
  - 유한번 진입 시도를 한 후에는 critical section에 진입할 수 있다  
*-> 각 process 실행 속도, n개의 process 간의 상대적인 실행 속도에 대한 가정은 하지 않음*

<br>

## 5.3 Peterson's Solution
- 두 process 간 solution
- software적인 해결 방법
- hardware 가정 -> load(메모리 -> CPU 레지스터로 복사), store(CPU 레지스터 -> 메모리 특정 위치 복사): atomic
- atomic: 한번에 하나씩 순차적으로
- int turn
  - 어느 프로세스가 자신의 critical section에 접근할 차례인지
  - turn == 0 : P0가 critical section에 진입할 차례 
- Boolean flag[2]
  - 해당 프로세스가 진입할 준비가 되었다
  - flag[i]==true : Pi is ready
```
// P0
do {
  flag[0]= true; //P0는 준비가 되었다
  turn = 1; //다음 critical section에 진입할 번호, P1이 critical section에 진입하기 원한다면 P1에게 허용
   while(flag[1] && turn ==1); 
    critical section
   flag[0] = false;
    remainder section
} while (true);
```
- P0가 critical section에 들어갈 수 있는 경우
  - flag[1] == false: P1이 remainder section에 있다면, flag[1]=false -> P0가 critical section에 들어갈 수 있음
  - turn == 0: P1이 entry section을 실행하는 도중, turn = 0으로 설정할 경우

- Mutual exclusion
  - 두 process가 모두 critical section에 들어갈 수 없음
  - turn 둘 중 하나만 가질 수 있음
- Progress, Bounded waiting
  - P0가 critical section에 진입하지 못하도록 막음 -> flag[1] = true, turn ==1 유일함
  - P1 들어갈 준비가 되지않았다면 flag[1]= false, P0은 들어갈 수 있음
  - P1 flag[1]=true -> turn ==0 or turn==1
  - turn ==0 -> P0 
  - turn ==1 -> P1이 critical section -> flag[1] = false로 재정의 -> P0 진입함
  - P1 flag[1] = true / turn =0 으로 지정 
  - P0 는 turn 값 못바꾸므로 P1이 들어간 후 들어갈 수 있기때문에 무한정 기다리지 않음

<br>

## 5.4 Synchronization Hardware
- 대부분 임계지역 문제를 쉽게 해결하기 위해 하드웨어 지원 제공
- CPU 하나 -> interrupt 끔 
- 현재 실행 중인 코드가 방해받지 않을 수 있음
- critical section 진입할때 interrupt 끔 -> multi processor 에서 적용하기 힘듦 
  - interrupt disable 메시지가 모든 처리기에 전달 -> 상당한 시간 소비, 전달이 각 CPU마다 critical section 진입에 지연
- test, set, swap -> atomic
- test_and_set: CPU 명령 -> 함수 형태지만 특정 메모리 위치에 대해 동작하는 CPU 명령임, 함수아님
```
//targer = 실행 대상의 memory 번지
boolean test_and_set (boolean *target){
  boolean rv = *target; //target 위치 저장
  *target = TRUE;
  retrun rv; // 임시 저장 target return
}
```
```
// 자신은 critical section에 들어가고 lock = true로 변하고 들어감
do 
{ 
  while (test_and_set(&lock));
    /* critical section */
  lock = false;
    /* remainder section */
} while(true);
```

*-> 동시에 시도하더라도 atomic하기 때문에 하나만 성공할 수 있음 *

- compare_and_swap
```
//value: 특정 메모리값 읽음
int compare_and_swap(int *value, int expected, int new_value){
  int temp = *value;
  if (*value == expected)
    *value = new_value
  return temp;
}
```

```
do 
{ 
  while (compare_and_swap(&lock,0,1) !=0) // 처음 0 == 0 만족해서 while 안들어감
    ;
    /* critical section */
  lock = 0;
    /* remainder section */
} while(true);
// critical section 진입을 무한히 시도해도 못들어갈 수도 있음
```

<br>

## 5.5 Mutex Locks
- 이전은 복잡함
- software 도구 개발
- 임계지역 진입 가능 여부 하는 boolean 변수
- acquire(): lock 얻음 / release(): lock 해제 -> atomic
- busy waiting/ spinlock -> lock 얻기위해 반복문

<br>

## 5.6 Semaphore
- mutex locks보다 정밀
- S - 정수형 변수
- wait()
  - P()
  - S가 양수가 될 때까지 기다림
  - S--;
- signal()
  - V()
  - S ++;
- 초기화 외, wait, signal로 접근
- Counting semaphore: (0~n) -> 가용자원 나타냄
- Binary semaphore: (0,1) -> mutex locks
- S1이 S2 먼저 동작
```
P1:
  S1;
  signal(synch);
  
P2:
  wait(synch);
  S2;
/*
P2가 먼저 실행하더라도 wait연산에서 기다림
P1으로 넘어가서 S1실행, signal -> P2의 S2 실행
S1 -> S2 강제
*/
```
- 같은 Semaphore에 대해서 두 process가 동시에 wait, signal 할 수 없음 -> atomic
- Semaphore구현 자체가 critical section 문제 -> wait, signal 이 critical section 내에
- busy waiting 을 통해 구현 -> semaphore를 사용해 보호하고자 하는 임계 지역이 짧은 경우, busy waiting이 오래 지속되지 않아서 괜찮지만 임계지역이 길다면 바람직한 해결책은 아님
- semaphore마다 waiting queue하나씩 만듦
```
typedef struct{
  int value; //현재 semaphore 값 가짐
  struct process *list; // semaphore에 대해 대기할 수 있는 wait queue, semaphore의 값을 기다리는 process들이 머물게 됨
}semaphore; 
```
- block: process를 wait queue에 넣는 동작
- wakeup: wait queue에 대기 중인 process가 있는 경우, 이를 꺼내서 ready queue로

### Deadlock and Starvation
- Deadlock: 교착 상태 - 영원히 발생하지 않을 상황을 무한정 기다림
- Starvation: signal 연산을 하지 않은 경우, wait queue에서 영원히 빠져나오지 못함
- Priority Inversion: 우선순위가 있는 process들 간에 semaphore를 사용하는 과정에서 발생할 수 있는 문제 -> 우선순위 역전

<br>

## 5.7 Classical Problems of Synchronization
- Bounded-buffer problem: 생산자 소비자 문제
  - mutex라는 semaphore: 생산자나 소비자가 bounded buffer에 접근하는 과정을 critical section으로 간주, 상호배제 보장 -> 0 or 1
  - 초기값: 1
  - full: buffer가 비어있는 경우 producer가 버퍼 채우는 것을 기다림 -> consumer 동작 제어/ 현재 buffer에 채워진 개수
  - empty: 빈 buffer가 생길 때까지 기다림 -> producer 동작 제어/ 현재 buffer의 빈칸의 개수
- Readers and Writers Problem: 
  - 다수의 병행 process -> 공유데이터 집합에 접근
  - reader: 공유하는 데이터 집합을 읽기만 -> 동시에 데이터 접근 가능 (데이터 변경하지 않으므로)
  - writer: 데이터에 대해 읽기, 쓰기 모두 함 (한번에 한 writer만 접근 가능)
  - read_count: 0 / 현재 몇개의 reader process들이 공유 데이터를 읽고 있는지
  - rw_mutex: reader, writer둘 다 접근, writer간의 상호배제 / 첫번째 reader, 마지막 reader도 접근
  - mutex: read_count -> 상호배제 보장 
- Dining-Philosophers Problem:
  - 모두 동시에 든다면 deadlock 발생
  - 좌석은 5개라면 철학자는 4명으로 여유를 만든다
  - 홀수 -> 왼부터, 짝수 -> 오른쪽부터

<br>

## 5.8 Monitors
- 프로세스 간의 동기화를 편하게
- abstract data type -> 모니터 내부의 함수들에 의해서만 공유 변수 접근
- 동기화와 관련된 공유변수/ 공유변수에 대한 접근 함수, 초기화 코드
- 항상 하나의 process 만이 활성화 -> 동기화에 대한 제약조건을 명시적으로 코딩할 필요 없음
- entry queue: 진입하고자 하는 process (한 번에 한 process만 진입가능)
- 일부 동기화 기법을 모델링하는데 충분한 기능을 제공하지 않기 때문에 부가적인 동기화 기법을 정의한다 -> condition variable
- Condition variable
  - semaphore와 비슷
  - x.wait() -> x.signal()까지 suspend
  - x.signal() -> suspend 된 processrk 가 없다면 효과 없음

<br>

## 5.11 The Deadlock Problem
- process 자원 사용에서 발생할 수 있는 문제
- 자원을 소유한 상태에서 다른 process가 소유하고 있는 자원 기다림
- starvation: 교착상태를 자원 뺏아서 해결하고 이 양보가 계속 발생할 수도 
- 아래 조건들이 모두 발생해야 Deadlock 발생
- Mutual exclusion
  - 상호 배제, 한번에 한 process가 자원 소유
- Hold and wait
  - Deadlock에 연루된 process -> 최소 하나의 자원을 소유한 상태에서, 다른 process가 이미 소유하고 있는 자원을 추가로 얻기를 기다림
- No preemption
  - process가 보유한 자원, 사용후 자발적으로 회수 -> 중간에 뺏을 수 없음
- Circular wait
  - cycle 형성
  - cycle이 없으면 deadlock 상태는 아님
  - instance 하나 -> cycle -> deadlock
  - 여러개 -> cycle이 있다고 해서 deadlock이 무조건은 아님
- Deadlock prevention: 한가지 조건을 선택해서 해당 조건이 절대 생기지 않도록 관리 -> 자원 사용에 제약 많아짐
- Deadlock avoidance
  - safe: deadlock과 거리가 먼 정상적인 상태
  - unsafe: deadlock이 될 수도 있는 상태
  - process 할당 요청, 이 요청에 대해 system 상태를 safe상태로 유지할 수 있도록 요청만 수락
  - unsafe 할 것 같다 -> 요청 거절
 
*-> deadlock 발생 전 사전 조치*

- 조치하고 있지 않다가 탐지하면 해결하는 방식 -> 대부분 이 방식 
