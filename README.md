# Thông tin sinh viên
24127329 - Lê Nguyễn Quốc Bảo - lnqbao2418@clc.fitus.edu.vn

# Link video Youtube
https://youtu.be/qCABidjHbnA

# Tiền đề để code chạy được
- Có thư mục "files" nằm bên trong "src" (mặc định bài nộp đã có)
    + Thư mục "files" có chứa 3 file .txt: defaultSlang.txt, history.txt và slang.txt (mặc định bài nộp đã có)

# Compile code
- Bước 1: Tạo 1 folder tên "bin" đặt trong thư mục hiện đang chứa folder "src" (mặc định bài nộp đã có)
- Bước 2: Chạy `javac -d bin src/*.java` để tạo các file .class trong thư mục "bin"

# Thực thi chương trình

## Terminal
- Bước 1: Di chuyển Terminal vào trong thư mục đang chứa "src" và "bin"
- Bước 2: Chạy `java -cp bin Main`

## File .jar
- Bước 1: Để file "SlangDictionary.jar" vào trong cùng thư mục với "src" và "bin"
- Bước 2: Di chuyển Terminal vào thư mục đang chứa "SlangDictionary.jar"
- Bước 3: Chạy `java -cp "SlangDictionary.jar" Main`

# Phần tự chấm điểm từng chức năng:
1/ Chức năng tìm kiếm theo slang word: 10đ
2/ Chức năng tìm kiếm theo definition, hiển thị ra tất cả các slang words mà trong defintion có chứa keyword gõ vào: 10đ
3/ Chức năng hiển thị history, danh sách các slang word đã tìm kiếm: 10đ
4/ Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người dùng, confirm có overwrite hay duplicate ra 1 slang word mới: 10đ
5/ Chức năng edit 1 slang word: 10đ
6/ Chức năng delete 1 slang word. Confirm trước khi xoá: 10đ
7/ Chức năng reset danh sách slang words gốc: 10đ
8/ Chức năng random 1 slang word (On this day slang word): 10đ
9/ Chức năng đố vui, chương trình hiển thị 1 random slang word, với 4 đáp án cho người dùng chọn: 10đ
10/ Chức năng đố vui, chương trình hiển thị 1 definition, với 4 slang words đáp án cho người dùng chọn: 10đ

=> Tổng điểm: 100đ

# GitHub link
https://github.com/QBBs24/24127329-SlangDictionary