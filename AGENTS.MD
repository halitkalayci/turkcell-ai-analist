## AGENTS.MD

> Bu dosya yapay zeka ile çalışırken yapay zekanın uyması gereken zorunlu sınırları çizer.
---

## 1) TEKNOLOJİ SEÇİMLERİ

### 1.1) BACKEND TEKNOLOJİLERİ

- Java 17+
- Spring BOOT 3.X
- PostgreSQL

### 1.2) FRONTEND TEKNOLOJİLERİ

- Nodejs
- React

## 2) SERVİSLER

Bu e-ticaret mikroservis projesinde yalnızca aşağıdaki servisler bulunmak zorundadır. Bu servisler dışında herhangi bir ekleme yapamazsın.

- Product Service (Ürün Servisi)
- Order Service (Sipariş Servisi)
- Gateway
- Eureka Server

## 3) CONTRACT-FIRST YAKLAŞIM

Tüm sistem contract-first çalışmak zorundadır. Sistemdeki herhangi bir web servisi oluşturulurken/güncellenirken o sistemdeki `docs/contracts` altından okunmalı, contract yoksa DUR ve kullanıcıyı uyar.


## 4) PLANLAMA VE ÇALIŞMA DÖNGÜSÜ

Verilen her promptta dosya düzenlemeden/eklemeden önce tüm dosya değişiklik dökümünü ver. Kullanıcı ONAYLAMADAN asla bir değişiklik yapma. Her promptta bu kural net bir şekilde geçerlidir.