
## เกี่ยวกับโปรเจกต์นี้

โปรเจกต์นี้เป็น REST API สำหรับจัดการเมนูร้านกาแฟ (เพิ่ม / ดู / แก้ไข / ลบเมนู) โดยเก็บข้อมูลไว้ใน memory (List) ยังไม่ได้เชื่อมฐานข้อมูลจริง เขียนตามหลักการแบ่งชั้น (Layered Design) แยกเป็น 3 ส่วนคือ Model, Service และ Controller ตามที่โจทย์กำหนด

## โครงสร้างโปรเจกต์

```
coffeeshop/
 └─ src/main/java/com/example/coffeeshop/
     ├─ CoffeeshopApplication.java   → จุดเริ่มรันโปรแกรม
     ├─ model/
     │   └─ Coffee.java              → เก็บโครงสร้างข้อมูลกาแฟ (id, name, price)
     ├─ service/
     │   └─ CoffeeService.java       → เก็บ logic และ List<Coffee> ใน memory
     └─ controller/
         └─ CoffeeController.java    → รับ HTTP request แล้วเรียกใช้ Service
```

## วิธีรันโปรเจกต์

1. เปิดโปรเจกต์ในโฟลเดอร์ `coffeeshop` ด้วย VS Code หรือ IDE ที่ถนัด
2. เปิด Terminal แล้วรันคำสั่ง (Windows PowerShell)

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   หรือถ้าลง Maven ไว้ในเครื่องแล้ว ใช้คำสั่งปกติได้เลย

   ```bash
   mvn spring-boot:run
   ```

3. รอจนเห็น log ว่า `Tomcat started on port 8080` แปลว่าใช้งานได้แล้ว
4. เปิด browser หรือ Postman แล้วยิงไปที่ `http://localhost:8080/coffees` เพื่อทดสอบ

> เมื่อรันแอปครั้งแรก ระบบจะใส่ข้อมูลตัวอย่างไว้ล่วงหน้า 2 รายการ (Espresso, Latte) ให้ทดสอบ GET ได้ทันที

## Endpoint ทั้งหมด

| # | Method | Path | คำอธิบาย |
|---|--------|------|----------|
| 1 | GET | `/coffees` | ดูเมนูกาแฟทั้งหมด |
| 2 | GET | `/coffees/{id}` | ดูเมนู 1 รายการตาม id |
| 3 | POST | `/coffees` | เพิ่มเมนูใหม่ |
| 4 | PUT | `/coffees/{id}` | แก้ไขเมนูเดิมตาม id |
| 5 | DELETE | `/coffees/{id}` | ลบเมนูตาม id |
| Bonus | GET | `/coffees/search?name=...` | ค้นหาเมนูจากชื่อ |

## ตัวอย่างการเรียกใช้งาน (curl)

```bash
# ดูเมนูทั้งหมด
curl.exe http://localhost:8080/coffees

# ดูเมนูตาม id
curl.exe http://localhost:8080/coffees/1

# เพิ่มเมนูใหม่
curl.exe -X POST http://localhost:8080/coffees \
     -H "Content-Type: application/json" \
     -d '{"name":"Cappuccino","price":60.0}'

# แก้ไขเมนู
curl.exe -X PUT http://localhost:8080/coffees/2 \
     -H "Content-Type: application/json" \
     -d '{"name":"Latte","price":50.0}'

# ลบเมนู
curl.exe -X DELETE http://localhost:8080/coffees/3

# ค้นหาเมนูจากชื่อ (bonus)
curl.exe "http://localhost:8080/coffees/search?name=latte"
```


## ผู้จัดทำ

| ชื่อ-นามสกุล | รหัสนักศึกษา |
|---|---|
| [นางสาวณัฐนันทน์ บุษดี] | [673380037-1] |
| [นางสาวศิริรัตน์ ชัยชนะ] | [673380060-6] |
