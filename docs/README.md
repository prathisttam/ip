# Simba User Guide

```
                        ____  _           _           
                      / ___|(_)_ __ ___ | |__   __ _ 
                      \\___ \\| | '_ ` _| | '_ \\ / |
                       ___) | | | | | | | |_) | (_| |
                      |____/|_|_| |_| |_|_.__/ \\__,_|
```
## 🐾 Introduction
**Simba** is a **task management chatbot** designed for users who prefer **command-line interactions**. It helps users 
manage their **to-dos, deadlines, and events**, allowing them to track tasks efficiently.

---
## 📂 Table of Contents
- [📜 Getting Started](#-getting-started)
- [📌 Initial Menu](#initial-menu)
- [✨ Features](#features)
    - [🎮 Echo Game](#echo-game-)
    - [📝 Task Manager](#task-manager)
- [🗂 Task Manager Features](#task-manager)
    - [1️⃣ Adding Todos](#1-adding-todos-todo)
    - [2️⃣ Adding Deadlines](#2-adding-deadlines-deadline)
    - [3️⃣ Adding Events](#3-adding-events-event)
    - [📋 Listing All Tasks](#4-listing-all-tasks-list)
    - [✔️ Marking Tasks as Done](#5-marking-tasks-as-done-mark)
    - [❌ Unmarking Tasks](#6-unmarking-tasks-as-incomplete-unmark)
    - [🗑️ Deleting Tasks](#7-deleting-tasks-delete)
    - [🔎 Finding Tasks](#8-finding-tasks-using-keyword-find)
    - [🚪 Exiting the Application](#9-exiting-the-application-bye)


## 📜 Getting Started
1. Ensure you have Java `17` or above installed on your computer.
2. Download the latest `.jar` from .
3. Copy the file to the folder you want to use as the home folder for your TaskList.
4. Open a command terminal, `cd` into the folder you put the jar file in and run the following:
```
java -jar ip.jar
```

## Initial Menu
When you start Simba, you will be presented with the following menu:

To proceed:
- Select **`1`** to play **Echo Game**.
- Select **`2`** to use **Task Manager**.
- Select **`3`** to **exit the application**.

## Features

> [!NOTE]
> Words enclosed within `<>` are placeholders for user input


## Echo game 
### 1. Echo
***Syntax:*** `<user input of string>`

Repeats what you input into the command line.

**Example:**
--------------------------------------------------------------
> hello copy cat

```
hello copy cat
```
--------------------------------------------------------------

## Task Manager
### 1. Adding todos: **`todo`**

**Syntax:** `todo <task description>`

Adds a new todo task to your list.

**Example:**
--------------------------------------------------------------
> todo user guide

```
Got it. I've added this task:
[T][ ] user guide
You have 1 tasks in the list
```
--------------------------------------------------------------

### 2. Adding deadlines: **`deadline`**

**Syntax** `deadline <task description> /by <date>`

**Example:**
--------------------------------------------------------------
> deadline read book /by Feb 22 2025

```
Got it. I've added this task:
[D][ ] read book (by: Feb 22 2025)
Now you have 2 tasks in the list.
```
--------------------------------------------------------------

### 3. Adding events: **`event`**

**Syntax** `event <task description> /from <start date> /to <end date>`

**Example:**
--------------------------------------------------------------
> event book club meeting /from tuesday /to thursday

```
Got it. I've added this task:
[E][ ] book club meeting (from: tuesday to: thursday)
You have 3 tasks in the list.
```
--------------------------------------------------------------

### 4. Listing all tasks: **`list`**

**Syntax** `list`

**Example:**
--------------------------------------------------------------
> list

```
1. [T][ ] user guide
2. [D][ ] read book (by: Feb 22 2025)
3. [E][ ] book club meeting (from: tuesday to: thursday)
```
--------------------------------------------------------------

### 5. Marking tasks as done: **`mark`**

**Syntax** `mark <task type> <task index in list>`

**Example:**
--------------------------------------------------------------
> mark 1

```
Nice! I’ve marked task as done:
[T][X] user guide
```
--------------------------------------------------------------

### 6. Unmarking tasks as incomplete: **`unmark`**

**Syntax** `unmark <task type> <task index in list>`

**Example:**
--------------------------------------------------------------
> unmark 1

```
OK, I've marked this task as not done yet:
[T][ ] user guide
```
--------------------------------------------------------------

### 7. Deleting tasks: **`delete`**

**Syntax** `delete <task type> <task index in list>`

**Example:**
--------------------------------------------------------------
> delete 1

```
Noted. I’ve removed this task:
[T][ ] user guide
Now you have 2 tasks in the list
```
--------------------------------------------------------------

### 8. Finding tasks using keyword: **`keyword`**

**Syntax** `find <keyword>`

**Example:**
--------------------------------------------------------------
> find book

```
Here is a list of your tasks:
1. [D][ ] read book (by: Feb 22 2025)
2. [E][ ] book club meeting (from: tuesday to: thursday)
```
--------------------------------------------------------------

### 9. Exiting the application: **`bye`**

**Syntax** `bye`

Saying bye for the first time exits the task Manager/ Echo game.
Saying “bye” for a second time or selecting 3 will then exit the application fully.

**Example:**
____________________________________________________________
> bye

```
Bye. Hope to see you again soon!
Choose an option:
1 - Play the game Echo!
2 - Manage Tasks
3 - Exit
Your choice:
```
> bye

```
Bye. Hope to see you again soon!
Process finished with exit code 0
```
____________________________________________________________


