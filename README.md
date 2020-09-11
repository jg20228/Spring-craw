# 제품 리뷰 앱을 위한 API 서버 - 네이버 크롤링

![blog](https://postfiles.pstatic.net/MjAyMDA4MjFfMTg5/MDAxNTk3OTg3NjQwMDg5.o3eOgyjWjM1qIALWjeBugxt8LpdmUwX2zu0iQPubNiog.UbDEE9QcmX5AWFjZltqc_Ow4XOGiI9KdymsgSfITHRog.PNG.getinthere/Screenshot_46.png?type=w773)

## 의존성

- JSoup
- Mustache
- MySQL
- Jpa
- Web
- Devtools
- Lombok

## 자식 데이터 삽입시 제약조건 위배 문제 해결법

```java
// 키워드 삭제시 부모 데이터 함께 삭제하려면 연관관계를 걸고 orphanRemoval를 건다.
@OneToMany(mappedBy = "keyword", orphanRemoval = true)
private List<Product> products;
```