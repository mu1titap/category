# 🔌 Adaptors

모두를 연결하라! Adoptors! <br>
취업/이직에 특화된 쉽고 편리한 멘토링 플랫폼. <br>
AI피드백, 화상•채팅 서비스, 간편한 멘토링 생성 기능 <br>
대용량 트래픽에 대한 분산처리가 가능한 MSA(Micro Service Architecture) 구조의 프로젝트입니다. <br><br>

### 특징

- EDA(Event Driven Design)를 적용, 카프카 이벤트 기반으로 느슨한 결합을 가집니다.
- 각 서비스들의 독립적 DB구조에서 효율적 집계를 담당하는 Batch-service.
- 카프카 소스/싱크 커넥트를 사용한 CDC 기반 데이터 동기화 처리.
- CQRS (Command and Query Responsibility Segregation) 적용. <br>
  쓰기와 읽기의 책임을 명확히 분리하였습니다.
- 헥사고날 아키텍처를 적용하여 기술 코드와 비즈니스 코드를 분리. <br>
  도메인로직에 집중 하여 기술적 요구사항에 빠르게 대응할 수 있도록 했습니다.

<hr>

- **개발 기간** : 2024.10.07 ~ 2024.12.15 (11주)
- **플랫폼** : Web
- **개발 인원** : 9명 <br><br>

<table align="center">
  <tbody align="center"> 
    <tr>
      <td align="center" valign="middle" width="100%">
        <img width="100%" src="./readme-assets/cover.png" />
      </td>
    </tr>
    <tr>
      <td align="center" valign="middle" colspan="2" width="50%">
        <table width="100%">
          <tr>
            <td align="center" valign="middle" width="50%">
              <img width="100%" src="./readme-assets/qr.png" />
            </td>
            <td align="center" valign="middle" width="50%">
              <img width="100%" src="./readme-assets/voltchar.webp" />
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </tbody>
</table> <br>

## 🔎 목차

<div align="center">

### <a href="#developers">🌟 팀원 구성</a>

### <a href="#techStack">🛠️ 기술 스택</a>

### <a href="#systemArchitecture">🌐 시스템 아키텍처</a>

### <a href="#skills">📲 기능 구성</a>

### <a href="#directories">📂 디렉터리 구조</a>

### <a href="#projectDeliverables">📦 프로젝트 산출물</a>

</div>
<br>

## 🌟 팀원 구성

<a name="developers"></a>
<div align="center">
<table align="center" width="100%" border="1">
  <tbody>
    <!-- 첫 번째 행 -->
    <tr>
      <td height="320px" align="center" colspan="2"> 
        <a href="">
          <img src="" width="160px" /> <br> 정훈석 <br>(Frontend)
        </a>
      </td>
      <td height="320px" align="center" colspan="2">
        <a href="">
          <img src="" width="160px" /> <br> 설찬우 <br>(Frontend)
        </a>
      </td>
      <td height="320px" align="center" colspan="2">
        <a href="">
          <img src="" width="160px" /> <br> 김예진 <br>(Frontend)
        </a>
      </td>
    </tr>
    <tr>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 김대희 <br>(Backend)
        </a>
      </td>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 김성태 <br>(Backend)
        </a>
      </td>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 강수빈 <br>(Backend)
        </a>
      </td>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 허정현 <br>(Backend)
        </a>
      </td>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 백승엽 <br>(Backend<br>&DevOps)
        </a>
      </td>
      <td height="320px" align="center">
        <a href="">
          <img src="" width="160px" /> <br> 오대관 <br>(DevOps)
        </a>
      </td>      
    </tr>
  </tbody>
</table>


<!-- <table>
    <tr>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  정훈석 <br>(Frontend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  설찬우 <br>(Frontend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  김예진 <br>(Frontend) </a> <br></td>
    </tr>
    <tr>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  김대희 <br>(Frontend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  김성태 <br>(Frontend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  강수빈 <br>(Frontend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  허정현 <br>(Backend) </a> <br></td>
        <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  오대관 <br>(Backend) </a> <br></td>
            <td height="320px" align="center"> <a href="">
            <img src="" width="160px" /> <br>  김대희 <br>(Backend) </a> <br></td>
    </tr>
    <tr>
      <td width="160px">
        <sub>
          공통 컴포넌트, Product, ProductList, Review, Category, 로그인/회원가입 퍼블리싱 및 연동
        </sub>
      </td>
      <td width="160px">
        <sub>
          Best, Brand, Event, 장바구니, 마이페이지 퍼블리싱 및 연동
        </sub>
      </td>
      <td width="160px">
        <sub>
        CI/CD 환경 및 인프라 구축 <br>
        <strong>API</strong>: Category, ProductList, Media, Brand, Wishlist & Like, 장바구니, 주문 <br>
        <strong>데이터 가공</strong>: Category, Brand, Product, Media
        </sub>
      </td>
      <td width="160px">
        <sub>
          Spring Security, JWT <br>
          <strong>API</strong>: Review, Statistics, 로그인/회원가입, 마이페이지 <br>
        <strong>데이터 가공</strong>: Review
        </sub>
        </sub>
      </td>
      <td width="160px">
        <sub>
          <strong>API</strong>: Product, Product Option <br>
        <strong>데이터 가공</strong>: ProductOption
      </td>
    </tr>
</table> -->
</div>
<br>

## 🛠️ 기술 스택

<a name="techStack"></a>

### Frontend

<!--  toDo
<div align="center">

![VSCode](https://img.shields.io/badge/VisualStudioCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white)<br>
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white)<br>
![React](https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white)
![NEXT.JS](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=Next.js&logoColor=white)
![Tailwind](https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=TailwindCSS&logoColor=white)
![ShadcnUI](https://img.shields.io/badge/shadcnui-000000.svg?style=for-the-badge&logo=shadcnui&logoColor=#000000)
![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white)
</div>

- **Language |** TypeScript 5.5.4
- **Runtime Environment |** Node.js 20.16.0
- **Framework |** Next.js 14.2.7, Tailwind CSS 3.4.1
- **Library |** Shadcn UI
- **IDE |** Visual Studio Code 1.93.1
- **Deploy |** Vercel -->

### Backend

<!-- todo

<div align="center">

![IntelliJ IDEA](https://img.shields.io/badge/intellijidea-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white)
![VSCode](https://img.shields.io/badge/VisualStudioCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white)<br>
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![MySQL](https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MySQL&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)<br>
![Spring Boot](https://img.shields.io/badge/springboot-6DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white)
![SpringSecurity](https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
</div>

- **Language |** Java 17, Python 3.12.4
- **Framework |** Spring Boot 3.2.9
- **Library |** Spring Data JPA, Querydsl 5.0.0
- **Database |** MySQL 8.0.38, Redis 7.2
- **IDE |** IntelliJ IDEA 2024.2 (Ultimate Edition), Visual Studio Code 1.93.1
- **Build Tool |** Gradle 8.8.0 -->

### DevOps

<!-- todo
<div align="center">

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)<br>
![Amazon EC2](https://img.shields.io/badge/amazonec2-FF9900.svg?style=for-the-badge&logo=amazonec2&logoColor=white)
![Amazon Route 53](https://img.shields.io/badge/amazonroute53-8C4FFF.svg?style=for-the-badge&logo=amazonroute53&logoColor=white)
![Application Load Balancer](https://img.shields.io/badge/awselasticloadbalancing-8C4FFF.svg?style=for-the-badge&logo=awselasticloadbalancing&logoColor=white)
</div> -->

### Collaboration

<div align="center">

![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)
![Notion](https://img.shields.io/badge/notion-000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
</div>
<br>

## 🌐 시스템 아키텍처

<!--  todo
<a name="systemArchitecture"></a>
<div align="center"> 

<img src="./readme-assets/architecture.png"/>
</div> -->
<br>

## 📲 기능 구성

<a name="skills"></a>
<div align="center"> 
<!-- <table>
  <tbody align="center"> 
    <tr> <th style="text-align: center"> Main Page </th> <th style="text-align: center"> Sign Up / Sign In </th> <th style="text-align: center"> Brand </th> </tr>
    <tr> <td width="33%"><img width="100%" src="./readme-assets/main_page.gif"/></td> 
    <td width="33%"><img width="100%" src="./readme-assets/signin_signup.gif"/></td> 
      <td width="33%"><img width="100%" src="./readme-assets/brand.gif"/></td> </tr>
    <tr> <th style="text-align: center"> Product Detail </th> <th style="text-align: center"> Basket </th> <th style="text-align: center"> Product List Infinite Scroll </th> </tr>
    <tr> <td width="33%"><img width="100%" src="./readme-assets/product_detail.gif"/></td> <td width="33%"><img width="100%" src="./readme-assets/basket.gif"/></td> 
      <td width="33%"><img width="100%" src="./readme-assets/product_list_infinite_scroll.gif"/></td> </tr>
  </tbody>
</table> -->
</div>
<br>

## 📂 디렉터리 구조

<!-- todo
<a name="directories"></a>
### Frontend
<details align="left">
  <summary>
    자세히
  </summary>

  ```
  📦frontend
 ┣ 📂.husky
 ┣ 📂public
 ┃ ┣ 📂assets
 ┃ ┃ ┗ 📂fonts
 ┣ 📂src
 ┃ ┣ 📂actions
 ┃ ┃ ┣ 📂auth
 ┃ ┃ ┣ 📂basket
 ┃ ┃ ┣ 📂best
 ┃ ┃ ┣ 📂brand
 ┃ ┃ ┣ 📂category
 ┃ ┃ ┣ 📂event
 ┃ ┃ ┣ 📂like
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┣ 📂order
 ┃ ┃ ┣ 📂product
 ┃ ┃ ┗ 📂review
 ┃ ┣ 📂app
 ┃ ┃ ┣ 📂(auth)
 ┃ ┃ ┃ ┣ 📂sign-in
 ┃ ┃ ┃ ┃ ┣ 📂find-account
 ┃ ┃ ┃ ┃ ┣ 📂find-result
 ┃ ┃ ┃ ┣ 📂sign-up
 ┃ ┃ ┃ ┃ ┣ 📂phone
 ┃ ┃ ┃ ┃ ┣ 📂simple
 ┃ ┃ ┣ 📂(main)
 ┃ ┃ ┃ ┣ 📂best
 ┃ ┃ ┃ ┃ ┣ 📂gift
 ┃ ┃ ┃ ┃ ┣ 📂popular
 ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┣ 📂event
 ┃ ┃ ┣ 📂api
 ┃ ┃ ┃ ┗ 📂auth
 ┃ ┃ ┃ ┃ ┗ 📂[...nextauth]
 ┃ ┃ ┣ 📂basket
 ┃ ┃ ┃ ┣ 📂regular
 ┃ ┃ ┣ 📂brand
 ┃ ┃ ┃ ┣ 📂favorite
 ┃ ┃ ┣ 📂category
 ┃ ┃ ┣ 📂config
 ┃ ┃ ┣ 📂context
 ┃ ┃ ┣ 📂event-detail
 ┃ ┃ ┃ ┣ 📂[id]
 ┃ ┃ ┃ ┃ ┣ 📂@category
 ┃ ┃ ┃ ┃ ┣ 📂@relation
 ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┃ ┣ 📂beautysize
 ┃ ┃ ┃ ┃ ┣ 📂beauty
 ┃ ┃ ┃ ┣ 📂delivery-info
 ┃ ┃ ┃ ┃ ┣ 📂account
 ┃ ┃ ┃ ┃ ┣ 📂add
 ┃ ┃ ┃ ┃ ┣ 📂modify
 ┃ ┃ ┃ ┣ 📂modPassword
 ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┃ ┣ 📂review
 ┃ ┃ ┃ ┃ ┣ 📂products
 ┃ ┃ ┃ ┣ 📂searchwish
 ┃ ┃ ┃ ┃ ┣ 📂events
 ┃ ┃ ┣ 📂order
 ┃ ┃ ┣ 📂product
 ┃ ┃ ┃ ┣ 📂[[...slug]]
 ┃ ┃ ┣ 📂product-detail
 ┃ ┃ ┃ ┣ 📂[id]
 ┃ ┃ ┃ ┃ ┣ 📂@review
 ┃ ┃ ┃ ┃ ┣ 📂@summary
 ┃ ┃ ┣ 📂providers
 ┃ ┃ ┣ 📂reviews
 ┃ ┃ ┃ ┗ 📂[id]
 ┃ ┣ 📂components
 ┃ ┃ ┣ 📂basket
 ┃ ┃ ┣ 📂best
 ┃ ┃ ┣ 📂brand
 ┃ ┃ ┣ 📂category
 ┃ ┃ ┣ 📂dummy
 ┃ ┃ ┣ 📂event
 ┃ ┃ ┃ ┣ 📂detail
 ┃ ┃ ┣ 📂find-account
 ┃ ┃ ┣ 📂icons
 ┃ ┃ ┃ ┣ 📂auth-service-header
 ┃ ┃ ┃ ┣ 📂basket
 ┃ ┃ ┃ ┣ 📂best
 ┃ ┃ ┃ ┣ 📂bottom-navigation
 ┃ ┃ ┃ ┣ 📂brand
 ┃ ┃ ┃ ┣ 📂footer
 ┃ ┃ ┃ ┣ 📂main-header
 ┃ ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┃ ┣ 📂product
 ┃ ┃ ┃ ┣ 📂product-cat-info-header
 ┃ ┃ ┃ ┣ 📂product-detail
 ┃ ┃ ┃ ┣ 📂product-list-header
 ┃ ┃ ┃ ┣ 📂review
 ┃ ┃ ┃ ┣ 📂sign-in
 ┃ ┃ ┃ ┣ 📂sign-up
 ┃ ┃ ┃ ┗ 📂to-top-button
 ┃ ┃ ┣ 📂layout
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┃ ┣ 📂myBeautySize
 ┃ ┃ ┃ ┣ 📂myDelivery
 ┃ ┃ ┃ ┃ ┣ 📂addDelivery
 ┃ ┃ ┃ ┃ ┣ 📂modifyDelivery
 ┃ ┃ ┃ ┣ 📂myInfoCategory
 ┃ ┃ ┃ ┣ 📂myInfoLookUp
 ┃ ┃ ┃ ┣ 📂myInfoTop
 ┃ ┃ ┃ ┣ 📂myPassword
 ┃ ┃ ┃ ┣ 📂myReview
 ┃ ┃ ┃ ┣ 📂myWish
 ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┣ 📂order
 ┃ ┃ ┣ 📂product
 ┃ ┃ ┣ 📂product-detail
 ┃ ┃ ┣ 📂rank
 ┃ ┃ ┣ 📂review
 ┃ ┃ ┣ 📂sign-in
 ┃ ┃ ┣ 📂sign-up
 ┃ ┃ ┃ ┣ 📂simple
 ┃ ┃ ┃ ┃ ┣ 📂esseitial-form-item
 ┃ ┃ ┃ ┃ ┣ 📂optional-form-item
 ┃ ┃ ┣ 📂ui
 ┃ ┃ ┗ 📂util
 ┃ ┣ 📂datas
 ┃ ┃ ┗ 📂dummy
 ┃ ┃ ┃ ┣ 📂basket
 ┃ ┃ ┃ ┣ 📂best
 ┃ ┃ ┃ ┣ 📂brand
 ┃ ┃ ┃ ┣ 📂category
 ┃ ┃ ┃ ┣ 📂event
 ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┃ ┣ 📂product
 ┃ ┃ ┃ ┣ 📂review
 ┃ ┃ ┃ ┗ 📂sign-up
 ┃ ┣ 📂hooks
 ┃ ┣ 📂lib
 ┃ ┣ 📂types
 ┣ 📜.eslintrc.json
 ┣ 📜.gitignore
 ┣ 📜.prettierignore
 ┣ 📜.prettierrc.json
 ┣ 📜components.json
 ┣ 📜next.config.mjs
 ┣ 📜package-lock.json
 ┣ 📜package.json
 ┣ 📜postcss.config.mjs
 ┣ 📜README.md
 ┣ 📜tailwind.config.ts
 ┗ 📜tsconfig.json
  ```
</details> -->

<details>
  <summary>
    자세히
  </summary>

<!-- todo -->

  ```
  📦backend
 ┣ 📂.github
 ┃ ┣ 📂ISSUE_TEMPLATE
 ┃ ┣ 📂workflows

  ```

</details>
<br>

## 📦 프로젝트 산출물

<a name="projectDeliverables"></a>
<h3>💡 이벤트 스토밍</h3>
<div align="center"> 

<img src=""/>
</div>

<h3>🗄️ ERD</h3>
<div align="center"> 
<!-- todo
<img src="./readme-assets/erd_image.png"/> -->
</div>




<h3><a href="" target="_blank">📅 WBS</a></h3>

<h3><a href="" target="_blank">📋 요구사항 정의서</a></h3>

<h3><a href="" target="_blank">📡 API 명세서</a></h3>