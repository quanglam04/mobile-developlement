# Coding Rules - Team BTL Android

> Một số rule cần tuân thủ khi phát triển tính năng/màn hình mới để đồng bộ cả team.
 
---

## 1. Khi tạo màn hình mới

**Vị trí:** `ui/screen/<tên_màn_hình>/`

**Quy tắc:**
- Mỗi màn hình tạo **1 folder riêng** trong `ui/screen/`. Tên folder viết **lowercase**, ví dụ: `login/`, `profile/`, `settings/`.
- Trong folder đó tạo **2 file**:
    - `XxxScreen.kt` — chứa giao diện UI (Composable)
    - `XxxViewModel.kt` — chứa logic xử lý dữ liệu

```
ui/screen/
├── home/
│   ├── HomeScreen.kt
│   └── HomeViewModel.kt
├── login/
│   ├── LoginScreen.kt
│   └── LoginViewModel.kt
└── profile/
    ├── ProfileScreen.kt
    └── ProfileViewModel.kt
```

- File Screen chỉ chứa **UI**, không gọi API hay xử lý logic trực tiếp. Mọi dữ liệu lấy từ ViewModel.
- Tên function Composable đặt theo format: `XxxScreen()`, ví dụ `HomeScreen()`, `LoginScreen()`.

---

## 2. Khi xử lý logic của 1 màn hình (ViewModel)

**Vị trí:** Cùng folder với Screen, file `XxxViewModel.kt`

**Quy tắc:**
- ViewModel kế thừa `androidx.lifecycle.ViewModel`.
- Dữ liệu trạng thái dùng `StateFlow` hoặc `MutableStateFlow`, **không dùng LiveData** (vì project dùng Compose).
- Gọi API, truy vấn database, xử lý logic **đều viết trong ViewModel**, không viết trong Screen.

```kotlin
// Ví dụ LoginViewModel.kt
class LoginViewModel : ViewModel() {
 
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
 
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            // Gọi API login ở đây
            _isLoading.value = false
        }
    }
}
```

```kotlin
// Trong LoginScreen.kt - lấy dữ liệu từ ViewModel
@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val isLoading by viewModel.isLoading.collectAsState()
 
    // Dùng isLoading để hiển thị UI
}
```
 
---

## 3. Khi thêm Route

**Vị trí:** `ui/navigation/`

**Quy tắc — 3 bước bắt buộc:**

**Bước 1:** Thêm route vào `Routes.kt`
```kotlin
object Login : Routes("login")
```

**Bước 2:** Thêm composable vào `AppNavigation.kt`
```kotlin
composable(Routes.Login.route) {
    LoginScreen()
}
```

**Bước 3:** Điều hướng từ màn hình khác
```kotlin
// Điều hướng bình thường
navController.navigate(Routes.Login.route)
 
// Điều hướng và xóa màn hình trước khỏi back stack
navController.navigate(Routes.Home.route) {
    popUpTo(Routes.Login.route) { inclusive = true }
}
```
 
---

## 4. Khi gọi API (Retrofit)

**Vị trí:** `data/remote/`

**Quy tắc:**
- Mỗi nhóm API tạo **1 interface** trong `data/remote/`, ví dụ `AuthApi.kt`, `TaskApi.kt`.
- Retrofit instance tạo **1 lần duy nhất** trong file `data/remote/RetrofitClient.kt` (singleton).
- **Không** gọi Retrofit trực tiếp trong Screen hay ViewModel. Phải đi qua Repository.

```kotlin
// data/remote/AuthApi.kt
interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
```
 
---

## 5. Khi truy vấn Database (Room)

**Vị trí:** `data/local/`

**Quy tắc:**
- Entity (bảng) đặt trong `data/model/`, ví dụ `Task.kt`.
- DAO đặt trong `data/local/`, ví dụ `TaskDao.kt`.
- Database class đặt trong `data/local/AppDatabase.kt` — chỉ tạo **1 file duy nhất** cho cả app.

```
data/
├── local/
│   ├── AppDatabase.kt      ← Database chung
│   └── TaskDao.kt           ← DAO cho từng bảng
├── model/
│   └── Task.kt              ← Entity / Data class
└── remote/
    └── AuthApi.kt            ← API interface
```
 
---

## 6. Khi viết Repository

**Vị trí:** `data/repository/`

**Quy tắc:**
- Repository là **cầu nối** giữa ViewModel và data source (API + Database).
- Mỗi tính năng chính tạo **1 Repository**, ví dụ `AuthRepository.kt`, `TaskRepository.kt`.
- ViewModel **chỉ gọi Repository**, không import trực tiếp API hay DAO.

```
ViewModel  →  Repository  →  API (remote) hoặc DAO (local)
```

```kotlin
// data/repository/TaskRepository.kt
class TaskRepository(
    private val taskApi: TaskApi,
    private val taskDao: TaskDao
) {
    suspend fun getTasks(): List<Task> {
        return try {
            val response = taskApi.getTasks()
            taskDao.insertAll(response)  // Cache vào local
            response
        } catch (e: Exception) {
            taskDao.getAllTasks()  // Offline thì lấy từ local
        }
    }
}
```
 
---

## 7. Component dùng chung

**Vị trí:** `ui/components/`

**Quy tắc:**
- Những Composable dùng lại ở **2 màn hình trở lên** thì tách ra `ui/components/`.
- Đặt tên rõ ràng: `CustomButton.kt`, `LoadingDialog.kt`, `TopBar.kt`.
- Nếu component chỉ dùng cho **1 màn hình duy nhất**, để trong folder của màn hình đó, không bỏ vào `components/`.

---

## 8. Quy tắc đặt tên

| Loại | Quy tắc | Ví dụ |
|------|---------|-------|
| Package/Folder | lowercase | `home`, `login`, `remote` |
| File Kotlin | PascalCase | `HomeScreen.kt`, `TaskDao.kt` |
| Composable function | PascalCase | `HomeScreen()`, `CustomButton()` |
| Biến / hàm thường | camelCase | `isLoading`, `getUserData()` |
| Constant | UPPER_SNAKE | `BASE_URL`, `DB_NAME` |
| Route string | lowercase | `"home"`, `"login"`, `"task_detail"` |
 
---

## 9. Quy tắc chung

- **Không commit code lỗi**. Build thành công rồi mới push.
- **Không sửa file của người khác** khi chưa trao đổi. Nếu cần sửa file chung (`AppNavigation.kt`, `AppDatabase.kt`), báo trong nhóm trước.