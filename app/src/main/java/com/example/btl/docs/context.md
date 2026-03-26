# Ứng dụng quản lý công việc cá nhân
# Cấu trúc folder
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

# Công nghệ sử dụng 
```
| Công nghệ | Version | Mục đích |
| --------- | ------- | -------- |
| Kotlin | 2.0.21 | Ngôn ngữ lập trình chính |
| Jetpack Compose | BOM 2024.09.00 | Xây dựng giao diện UI |
| Material3 | (theo Compose BOM) | Design system, component UI |
| Navigation Compose | 2.8.5 | Điều hướng giữa các màn hình |
| Retrofit | 2.11.0 | Gọi REST API |
| OkHttp | 4.12.0 | HTTP client, logging request/response |
| Gson | 2.11.0 | Parse JSON từ API |
| Firebase Messaging | BOM 33.9.0 | Thông báo push |
| Room | 2.6.1 | Lưu trữ task offline (SQLite) |
| Kotlin Coroutines | 1.10.1 | Xử lý bất đồng bộ |
| Vico | 2.0.1 | Biểu đồ tròn, cột cho màn hình thống kê |
| WorkManager | 2.10.0 | Lên lịch thông báo, nhắc nhở task |
| iText PDF | 5.5.13.4 | Export báo cáo thống kê ra PDF |
| DataStore | 1.1.3 | Lưu cài đặt dark/light mode, giờ DND |

```