# 📱 Giới thiệu

> Bài tập lớn môn thiết kế ứng dụng di động

---

## 👥 Thành viên nhóm

| Họ tên          | MSSV       | Vai trò |
| --------------- | ---------- | ------- |
| Trịnh Quang Lâm | B22DCCN482 |         |
| Vũ Nhân Kiên    | B22DCCN434 |         |
| Nguyễn Anh Tuấn |            |         |
| Vũ Thế Văn      |            |         |

---

## 🛠 Công nghệ sử dụng

| Công nghệ | Version | Mục đích |
| --------- | ------- | -------- |
|           |         |          |
|           |         |          |
|           |         |          |

---

## ⚙️ Yêu cầu môi trường

| Công cụ        | Version                             |
| -------------- | ----------------------------------- |
| Android Studio | Otter 2025.2.x                      |
| JDK            | 17 (Bundled JDK của Android Studio) |
| compileSdk     | 35                                  |
| minSdk         | 24                                  |

---

## 🚀 Hướng dẫn chạy project

```bash
# 1. Clone repo
git clone <repo-url>

# 2. Mở bằng Android Studio

# 3. Sync Gradle và Run
```

---

## 📁 Cấu trúc project

```
com/example/btl/
│
├── data/
│   ├── model/          # Data class — ánh xạ dữ liệu từ API trả về
│   ├── remote/         # Gọi API: ApiService, RetrofitClient
│   └── local/          # Lưu trữ cục bộ: Room DAO (nếu có)
│
├── repository/         # Trung gian giữa ViewModel và data source
│                       # Mọi logic gọi API đều đi qua đây
│
├── ui/
│   ├── theme/          # Màu sắc, font chữ, theme toàn app
│   ├── components/     # Các Composable dùng chung nhiều màn hình
│   └── screen/         # Mỗi màn hình gồm 2 file:
│       ├── home/
│       │   ├── HomeScreen.kt       # Giao diện (chỉ hiển thị)
│       │   └── HomeViewModel.kt    # Xử lý logic, giữ state
│       └── splash/
│           └── SplashScreen.kt
│
└── utils/              # Hàm tiện ích dùng chung (extension, helper...)
```

### Luồng hoạt động

```
Screen  →  ViewModel  →  Repository  →  API / Local DB
  ↑______________________________________________|
               (trả kết quả về, cập nhật UI)
```

**Nguyên tắc:**

- `Screen` — chỉ hiển thị, không chứa logic
- `ViewModel` — xử lý sự kiện từ user, giữ trạng thái UI
- `Repository` — nơi duy nhất gọi API hoặc truy xuất database
- `data/model` — data class, không chứa logic

---
