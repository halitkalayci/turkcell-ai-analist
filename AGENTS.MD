## AGENTS.MD

> Bu dosya yapay zeka ile çalışırken yapay zekanın uyması gereken zorunlu sınırları çizer.
---

## 1) TEKNOLOJİ SEÇİMLERİ

### 1.1) BACKEND TEKNOLOJİLERİ

- Java 17+
- Spring BOOT 3.X
- H2 In-Memory

### 1.2) FRONTEND TEKNOLOJİLERİ

- Nodejs
- React
- Vite

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

## 5) UYDURMA YOK (NO INVENTING)

Herhangi bir konuda bir karar vermen gerektiğinde DUR ve kullanıcıya sor. Kesinlikle hiç bir kararı uydurma durumu söz konusu olamaz.

## 6) ÇIKTI FORMATI

- Kesinlikle hiç bir emoji kullanma.
- Kurumsal resmi bir dil tercih et.
- Değişikliklerden etkilenen servisler, bu servislerin gerekli ise
restart terminal komutları.

## 7) ÇALIŞTIRMA VE DEBUGGING

- Her değişiklik sonrası o değişikliklerin etkilendiği uygulamaları listele, bu uygulamaları durdurup-yeniden başlatmak gerekiyor mu? Bunları raporla. Eğer gerekiyorsa o uygulama için yazılacak tüm terminal
komutlarını sırayla ver.

- Kendi başına asla çalıştırma amaçlı terminal komutu çalıştırma.