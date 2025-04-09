# 📋 ToDo Application

This is a simple and user-friendly ToDo Android application that enables users to manage their daily tasks effectively. Users can add tasks, set reminders, receive notifications, and mark tasks as completed.

---

## ✨ Features

- ✅ Add new tasks with reminders
- 📝 Edit existing tasks
- ❌ Delete tasks when not needed
- 🔔 Get notifications for upcoming tasks
- ✔️ Mark tasks as done

---

## 🛠 Tech Stack

- ⚙️ Java
- ⚙️ Kotlin
- 📱 Android Studio (UI, Emulator, Project Host)

---

## 🚀 Getting Started

To run this project on your local machine:

1. Clone the repo:
   ```bash
   git clone https://github.com/nayek04/todo-application.git

## Acknowledgements

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)


## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.

