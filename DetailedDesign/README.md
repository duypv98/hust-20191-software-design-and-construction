## README.md
---
Phân công công việc:
1. Thiết kế UI, Class Design theo các Use case:
- Việt Duy: Checkin vé một chiều.
- Thành Duy: Checkout vé một chiều.
- Văn Duy: Checkin, checkout thẻ trả trước.
- Ngọc Hải: Checkin, checkout vé 24h.
* Chi tiết các màn hình UI: 
- Việt Duy: Checkin vé một chiều thành công, thất bại (khi ga checkin nằm ngoài khoảng ghi trên vé).
- Thành Duy: Checkout vé một chiều thành công, thất bại (khi phí thực tế > phí trên vé); màn hình chờ thẻ / vé.
- Văn Duy: Checkin thẻ trả trước thành công, thất bại (khi số dư < base fare); checkout thẻ trả trước thành công, thất bại (khi không đủ số dư) 
- Ngọc Hải: Checkin, checkout vé 24h thành công, thất bại (khi hết hạn vé); checkout vé 24h thành công.
Xem việc đặt sai vào máy nhận diện không gây ra sự thay đổi của màn hình chờ.
2. Data modeling cả nhóm thiết kế chung.
3. Phân công review:
- Thành Duy cho Việt Duy.
- Việt Duy cho Văn Duy.
- Văn Duy cho Ngọc Hải.
- Ngọc Hải cho Thành Duy
