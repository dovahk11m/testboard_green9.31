-- data.sql

-- 10개의 더미 회원 데이터 삽입 (비밀번호는 모두 1234)
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('김철수', 'chulsoo@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('이영희', 'younghee@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('박민준', 'minjun@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('최서연', 'seoyeon@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('정하늘', 'haneul@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('윤지아', 'jia@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('장우빈', 'woobin@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('임소은', 'soeun@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('홍길동', 'gildong@test.com', '1234', 'USER', NOW());
INSERT INTO member_tb (name, email, password, role, created_at) VALUES ('고은별', 'eunbyeol@test.com', '1234', 'USER', NOW());

-- data.sql (member_tb INSERT 구문 뒤에 추가)

-- 10명의 회원이 각각 3개씩 작성한 총 30개의 게시글 데이터 삽입

-- 1. 김철수 (member_id: 1)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('철수의 첫 글', '안녕하세요. Spring Boot 게시판에 오신 것을 환영합니다.', 1, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('자바 JPA 스터디 모집', 'JPA 스터디 같이 하실 분 구합니다.', 1, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('H2 DB 사용 후기', '인메모리 DB라 가볍네요!', 1, NOW(), NOW());

-- 2. 이영희 (member_id: 2)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('영희의 Spring MVC 패턴', 'Controller, Service, Repository 구조는 정말 깔끔한 것 같아요.', 2, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('Mustache 템플릿 엔진 질문', '데이터 바인딩이 잘 안되는데 도와주세요!', 2, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('자바 최신 버전 뭐가 좋을까요?', 'Java 17 vs Java 21', 2, NOW(), NOW());

-- 3. 박민준 (member_id: 3)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('Minjun의 첫 포스팅', '간단한 CRUD 구현 중입니다.', 3, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('Lombok @Data의 위험성', '순환 참조 주의해야 합니다.', 3, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('게시판 디자인 피드백 받아요', 'CSS는 어떻게 입혀야 할까요?', 3, NOW(), NOW());

-- 4. 최서연 (member_id: 4)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('서연의 코딩 일기 1편', '오늘은 JPA Fetch Join을 배웠습니다.', 4, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('N+1 문제 해결! (감동)', 'Fetch Join이 답이었네요.', 4, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('페이징 DTO의 아름다움', 'PageResponseDTO 구조가 마음에 듭니다.', 4, NOW(), NOW());

-- 5. 정하늘 (member_id: 5)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('하늘의 SSR 경험', 'Server-Side Rendering은 초기 로딩이 빠르네요.', 5, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('SQL Injection 방지법?', 'PreparedStatement를 사용합시다.', 5, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('토큰 인증 vs 세션 인증', 'SSR에서는 세션이 편리하죠.', 5, NOW(), NOW());

-- 6. 윤지아 (member_id: 6)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('지아의 첫 게시물', '코드 리뷰 환영합니다!', 6, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('DTO 설계 잘하는 법', '필요한 데이터만 딱 넣는 것이 중요합니다.', 6, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('인증(401) 처리 방법', '로그인 페이지로 리다이렉트가 최고!', 6, NOW(), NOW());

-- 7. 장우빈 (member_id: 7)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('우빈의 개발 노트', 'Git 사용이 아직 어렵네요.', 7, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('데이터 바인딩의 원리', 'Mustache의 {{ }} 마법!', 7, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('Spring DevTools 너무 편해요', '자동 재시작 최고!', 7, NOW(), NOW());

-- 8. 임소은 (member_id: 8)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('소은의 Spring Boot 공부', 'Service 계층에 트랜잭션을 붙입시다.', 8, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('유효성 검증(Validation) 질문', '메시지를 어떻게 커스텀하나요?', 8, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('HTTP 상태 코드의 의미', '400, 401, 403 차이 정리', 8, NOW(), NOW());

-- 9. 홍길동 (member_id: 9)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('길동의 새 소식', '운영진에게 질문이 있습니다.', 9, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('MySQL 모드로 H2 사용하기', '유용한 설정이네요.', 9, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('글 삭제 로직 질문', '권한 체크는 Service에서 해야하죠?', 9, NOW(), NOW());

-- 10. 고은별 (member_id: 10)
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('은별의 마지막 글', '다들 수고 많으셨습니다.', 10, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('Spring Boot 구조 최종 점검', '모든 계층이 완벽합니다.', 10, NOW(), NOW());
INSERT INTO board_tb (title, content, member_id, created_at, updated_at) VALUES ('테스트 데이터 삽입 완료!', '이제 뷰를 만듭시다!', 10, NOW(), NOW());