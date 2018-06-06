
Q. threads/malloc.c에서 기본 페이지보다 작은 크기의 메모리 영역을 할당하기 위해 어떤 방법이 사용되나?

작은 크기의 메모리 할당 요청이 들어오면 함수 DIV_ROUND_UP을 통해 2의 제곱수로 만들어 디스크립터에 넘겨준다.

Q. threads/palloc.c와 lib/kernel/bitmap.c 내의 어떤 함수에 페이지 할당 알고리즘(First fit)이 구현되어 있는가?
Palloc_get_multiple 여러 개의 페이지를 할당하는 함수 호출.(start 포지션을 0으로 넣는다.)
여기서 bit_multiple_scan_flip?? 에서 bit scan을 호출한다. Bit scan에서0부터 비트를 차례대로 체크하여 연속 cnt개가 0이면 false를 리턴한다. False를 리턴하면 빈 공간이 cnt개가 있다는 뜻이므로 페이지 할당 가능. 이때 오프셋 0부터 차례대로 체크하기 때문에 first fit 알고리즘이다. 이때 오프셋에 저번 실행 알고리즘의 end offset을 넣어주면 next fit알고리즘을 만들 수 있다.  
