# 📱 Giới thiệu

> Bài tập lớn môn thiết kế ứng dụng di động

---

##  Thành viên nhóm

| Họ tên          | MSSV       | Vai trò |
| --------------- | ---------- | ------- |
| Trịnh Quang Lâm | B22DCCN482 |         |
| Vũ Nhân Kiên    | B22DCCN434 |         |
| Nguyễn Anh Tuấn |            |         |
| Vũ Thế Văn      |            |         |

---

##  Công nghệ sử dụng

| Công nghệ | Version | Mục đích |
| --------- | ------- | -------- |
| Kotlin | 2.0.21 | Ngôn ngữ lập trình chính |
| Jetpack Compose | BOM 2024.09.00 | Xây dựng giao diện UI |
| Material3 | (theo Compose BOM) | Design system, component UI |
| Navigation Compose | 2.8.5 | Điều hướng giữa các màn hình |
| Retrofit | 2.11.0 | Gọi REST API |
| OkHttp | 4.12.0 | HTTP client, logging request/response |
| Gson | 2.11.0 | Parse JSON từ API |
| Firebase Auth | BOM 33.9.0 | Đăng ký, đăng nhập, lấy lại mật khẩu |
| Firebase Firestore | BOM 33.9.0 | Cloud sync, sao lưu dữ liệu |
| Firebase Messaging | BOM 33.9.0 | Thông báo push |
| Room | 2.6.1 | Lưu trữ task offline (SQLite) |
| Kotlin Coroutines | 1.10.1 | Xử lý bất đồng bộ |
| Vico | 2.0.1 | Biểu đồ tròn, cột cho màn hình thống kê |
| WorkManager | 2.10.0 | Lên lịch thông báo, nhắc nhở task |
| iText PDF | 5.5.13.4 | Export báo cáo thống kê ra PDF |
| DataStore | 1.1.3 | Lưu cài đặt dark/light mode, giờ DND |

---

##  Yêu cầu môi trường

| Công cụ        | Version                             |
| -------------- | ----------------------------------- |
| Android Studio | Otter 2025.2.x                      |
| JDK            | 17 (Bundled JDK của Android Studio) |
| compileSdk     | 35                                  |
| minSdk         | 24                                  |

---

## Cấu hình biến môi trường
> Tạo file local.properties (root level)
```bash
sdk.dir=
BASE_URL=
API_KEY=
```

---

##  Hướng dẫn chạy project

```bash
# 1. Clone repo
git clone <repo-url>

# 2. Mở bằng Android Studio

# 3. Sync Gradle và Run
```

---

##  Cấu trúc project

```
com/example/btl/
│
├── data/
│   ├── model/                      # Data class 
│   └── local/                      # Lưu trữ cục bộ: Room DAO (nếu có)
│
├── repository/                     # Trung gian giữa ViewModel và data source
│                                   # Mọi logic gọi API đều đi qua đây
│
├── ui/
│   ├── nagivation/                 # Quản lý route toàn app
│   │   ├── Routes.kt               # Khai báo tên tất cả route
│   │   └── AppNavigation.kt        # NavHost — điều hướng giữa các màn hình
│   ├── theme/                      # Màu sắc, font chữ, theme toàn app
│   ├── components/                 # Các Composable dùng chung nhiều màn hình
│   └── screen/                     # Mỗi màn hình gồm 2 file:
│       ├── home/
│       │   ├── HomeScreen.kt       # Giao diện (chỉ hiển thị)
│       │   └── HomeViewModel.kt    # Xử lý logic, giữ state
│       └── splash/
│           └── SplashScreen.kt
│
└── utils/                          # Hàm tiện ích dùng chung (extension, helper...)
```

### Luồng hoạt động

```
Screen  →  ViewModel  →  Repository  →  DAO (Room) → SQLite
  ↑______________________________________________|
               (trả kết quả về, cập nhật UI)
```

**Nguyên tắc:**

- `Screen` — chỉ hiển thị, không chứa logic
- `ViewModel` — xử lý sự kiện từ user, giữ trạng thái UI
- `Repository` — nơi duy nhất gọi API hoặc truy xuất database
- `data/model` — data class, không chứa logic

---

### Tài liệu project (rule & coding convention)
> Tham khảo tại [đây](./app/src/main/java/com/example/btl/docs/docs.md)