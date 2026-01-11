# Dekanat UI

UI-first каркас для "Деканат 2.0" на Vaadin 24 + Spring Boot 3 (без авторизації та БД).

## Вимоги
- Java 17
- Maven 3.9+

## Запуск локально
```bash
mvn clean install
cd dekanat-app && mvn spring-boot:run
```

UI буде доступний за адресою: `http://localhost:${APP_PORT}`.

## Запуск через Docker
```bash
cp .env.example .env
docker compose --env-file .env -f docker/docker-compose.yml up --build
```

UI буде доступний за адресою: `http://localhost:${APP_PORT}`.

## Модульна структура
- `dekanat-app` — головний Spring Boot застосунок та оболонка (shell).
- `dekanat-ui-kit` — спільні UI-компоненти та layout.
- `dekanat-modules/*` — модулі. Повністю промальований `module-study-plans`, інші — скелети маршрутів.
