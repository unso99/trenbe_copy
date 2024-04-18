# myShop Android

**e커머스 개인프로젝트 안드로이드 repository(trenbi copy)**

[백엔드](https://github.com/unso99/trenbe_back)

## 프로젝트 특징

* Android Spring Boot을 기반으로 명품을 판매하는 e커머스 서비스

* 안드로이드와 백엔드를 분리하여 프로젝트 개발
    * 각 파트의 별도 Repository를 생성 후 작업
    * 안드로이드 : Android Studio 이용
    * 백엔드 : Spring Boot를 이용
* 로그인 처리는 Spring Boot Security와 Jwt Token방식으로 처리
* 초기 더미데이터는 DBeaver를 통한 DB에 직접 삽입

## 개요

* 명칭 : myShop

* 개발 인원 : 1명

* 개발 기간 : 2024.04.07 ~ 2024.04.18

* 주요 기능
    * 제품 조회, 검색 기능
    * 상세 주문 조회 기능
    * 주문 추가, 조회 기능
    * 장바구니 추가, 조회, 삭제 기능
    * 회원가입, 로그인 기능

* 개발 언어 : Kotlin

* 개발 환경 : Android Studio

* 형상 관리 툴 : git

## 사용 패키지
 * retrofit2, okHttp
   * rest api를 json으로 통신하기 위한 라이브러리
 * gson
   * json으로 데이터를 보내기 위한 라이브러리
 * livedata, viewmodel
   * mvvm 패턴을 위한 livedata, viewmodel를 사용하기 위한 라이브러리

## Contents

### 로그인 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/961d6d07-8021-4060-8621-ca3575a82309)

### 회원가입 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/127c7316-a034-43c3-80e1-387596b69df0)

### 메인 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/7f669e6c-4c62-44be-b2ba-7ee82d14790b)

### 제품 상세 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/2db9dc25-f549-4495-9b83-a2ddc3bce9b2)

### 검색 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/95978422-69cf-4f72-9743-670dcdcc7343)

### 마이 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/8e34b62d-e88f-4dbd-b52c-908ace7664c6)

### 장바구니 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/5ebfbc31-b5e8-4db7-943b-ee4d273e8f2f)

### 주문 페이지
![image](https://github.com/unso99/trenbe_copy/assets/94777814/d7a5e668-7418-426e-8011-6b5c80580764)



