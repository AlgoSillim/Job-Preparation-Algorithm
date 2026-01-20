# 📚 Algorithm Study

알고리즘 스터디를 위한 GitHub 기반 협업 레포지토리입니다.  
문제 관리, 풀이 공유, 코드 리뷰까지 **Issue + Pull Request + Projects**를 활용해 체계적으로 진행합니다.

<br>

## 🎯 스터디 목표

- 단순 풀이가 아닌 **사고 과정 공유**
- 알고리즘 선택 근거 설명 능력 강화
- 코드 리뷰를 통한 **가독성 / 설계 개선**
- 꾸준한 주차별 문제 해결 습관 형성

<br>

## 🧭 스터디 운영 개요

### 기본 흐름

1. **주차별 문제를 Issue로 등록**
2. **문제별 브랜치 생성 → 풀이 커밋**
3. **Pull Request 생성 후 상호 코드 리뷰**
4. **PR 머지 완료 후 Issue 닫기**

> 📌 문제 단위 관리 = Issue  
> 📌 풀이 단위 관리 = Pull Request

<br>

## 🧩 문제 선정 기준

### 📌 기본 원칙

- 구현/자료구조/그래프/탐색/DP/그리디 등 **코어 알고리즘 중심**
- 수학적 계산 위주의 문제는 제외
- 난이도 Gold 이상
- 푼 사람 수 1,000명 이상

### 🚫 제외 태그

- `math`
- `number_theory`
- `geometry`
- `probability`

### 🔍 문제 검색 쿼리 (solved.ac)

```text
-tag:math -tag:number_theory -tag:geometry -tag:probability s#1000.. *g
```

<br>

## 🗂 Issue (문제 관리)

### 📌 Issue 생성 목적

- 풀어야 할 문제를 명확히 정의
- 주차별 문제 관리
- 여러 명의 풀이(PR)를 하나의 문제로 묶기

👉 문제 등록 시 **Issue 템플릿을 반드시 사용**합니다.  
[문제 이슈 생성하기](/.github/ISSUE_TEMPLATE/algorithms-issue-template.md)

<br>

## 🌿 Branch 규칙

- **문제 단위 브랜치 생성**
- 브랜치명 형식: 이름_문제이름

<br>

## 📁 폴더 구조 규칙

풀이 코드는 **주차별 폴더** 아래에 정리합니다.
문제 이름은 특수 기호, 공백 없이 그대로 사용합니다.

### 📌 기본 구조

{월}월 {n}주차/ <br>
└─ <이름>_<문제이름>.<확장자> <br>
└─ 이호찬_ACMCraft.java

<br>

## 💬 커밋 컨벤션

### 📌 커밋 메시지 형식

[플랫폼] 문제번호/대회이름 : 문제 이름

### 📌 예시

- `[BOJ] 2559 : 수열`
- `[Programmers] 연습 문제 : 단속카메라`
- `[SWEA] 1953 : 탈주범 검거`

<br>

## 💻 Pull Request 규칙 (풀이 공유 & 리뷰)

### 📌 PR 생성 목적

- 개인 풀이 공유
- 코드 리뷰 및 개선
- 알고리즘 선택 근거 설명

👉 PR 생성 시 **PR 템플릿이 자동으로 적용**됩니다.  
[새 Pull Request 생성하기](/.github/pull_request_template.md)

<br>

## 🗃 GitHub Projects 사용법

### 주요 View

- **This Week (Issues)**
    - Week = @current
    - 이번 주에 풀 문제 목록

- **PR View**
    - Open 상태의 PR 관리
    - 리뷰 대상 PR 확인

- **Issue View**
    - 전체 문제 진행 현황 관리

<br>

## 🙌 기타

- 규칙은 스터디 진행 중 합의에 따라 개선될 수 있습니다.
- 모든 변경 사항은 문서로 남기는 것을 원칙으로 합니다.