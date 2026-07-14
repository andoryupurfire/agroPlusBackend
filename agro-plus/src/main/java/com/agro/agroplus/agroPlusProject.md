# 🌱 Agro+ — Documentación del Proyecto

> **Versión del documento:** 1.0  
> **Estado:** En desarrollo activo — Fase 2, Épica 2 en curso  
> **Stack:** Java 25 · Spring Boot 4.0.6 · PostgreSQL 15 · Docker · React + Vite (pendiente)

---

## ¿Qué es Agro+?

Agro+ es una **plataforma web de gestión agrícola comercial** que permite al productor y a su equipo técnico registrar, monitorear y analizar todo lo que ocurre en sus lotes, desde la siembra hasta la cosecha.

No es una app de recomendaciones genéricas — es un sistema que trabaja con los **datos propios de cada finca**, orientado a productores comerciales colombianos que toman decisiones empíricas sin datos sistematizados, lo que genera sobrecostos en insumos, pérdidas de rendimiento y exclusión de mercados formales.

### Cultivos objetivo (MVP)
| Cultivo | Ciclo | Presencia en Colombia |
|---|---|---|
| ☕ Café | Perenne (10–30 años) | Eje Cafetero, Huila, Nariño |
| 🍫 Cacao | Perenne (25–50 años) | Magdalena Medio, Arauca |
| 🍌 Plátano / Banano | Perenne cíclico (~11 meses) | Urabá, Magdalena, Llanos |
| 🌽 Maíz | Anual (3–5 meses) | Nacional |

---

## Módulos del sistema

| # | Módulo | Descripción |
|---|---|---|
| 1 | **Gestión de Lotes y Cultivos** | Registro de parcelas, variedad sembrada, fecha de siembra y ciclo esperado |
| 2 | **Bitácora de Campo** | Registro de labores, insumos aplicados, observaciones y fotos |
| 3 | **Seguimiento Fenológico** | Timeline visual por etapa con alertas de tareas según fase |
| 4 | **Dashboard de Indicadores** | Costo por hectárea, producción estimada vs real, comparativo histórico |
| 5 | **Asistente con IA** | 4 alternativas: asistente contextual, predicción de rendimiento, visión computacional, recomendaciones automáticas por etapa |
| 6 | **Reportes y Trazabilidad** | Documentos exportables para cooperativas, certificaciones y crédito rural |

---

## Requisitos funcionales — MVP

### Autenticación
- El agricultor puede registrarse con nombre, username y contraseña
- El sistema devuelve un token JWT al registrarse o iniciar sesión
- Todos los endpoints (excepto `/api/auth/**`) requieren token válido

### Gestión de fincas y lotes
- Un agricultor puede registrar muchas fincas
- Una finca puede tener muchos lotes
- Un lote tiene nombre, tamaño en hectáreas y pertenece a una finca

### Catálogos del sistema
- Los tipos de cultivo (Café, Cacao, Plátano, Maíz) son catálogo fijo — el agricultor no los modifica
- Las variedades son catálogo controlado con distancias y densidad precargadas
- Las etapas fenológicas son catálogo por tipo de cultivo con orden y duración en días

### Registro de cultivo
- Un lote puede tener solo **un RegistroCultivo activo** a la vez
- Para resembrar, el registro anterior debe estar en estado `finalizado`
- La etapa inicial se asigna automáticamente al momento de sembrar (orden = 1)
- Las etapas solo avanzan hacia adelante — no pueden retroceder

### Actividades / Bitácora
- Una actividad solo puede registrarse sobre un lote con RegistroCultivo activo
- Campos obligatorios: título, descripción, lote, etapa fenológica
- El archivo adjunto es opcional

### Calculadora de siembra
- Recibe: área en m² o hectáreas + variedad seleccionada
- Devuelve: cantidad estimada de plantas
- Fórmula: `área_m² / (dist_plantas × dist_surcos)`
- No escribe en base de datos — es solo informativa

---

## Reglas de negocio

1. Un agricultor tiene muchas fincas
2. Una finca tiene varios lotes
3. En cada lote solo se siembra un tipo de cultivo a la vez
4. El agricultor registra labores respecto a un lote
5. En v1 existe un solo rol: el del agricultor
6. Los tipos de cultivo y sus etapas son catálogo fijo del sistema
7. Las variedades son catálogo controlado — el agricultor no inventa nuevas
8. Las etapas tienen orden secuencial obligatorio — no se puede saltear
9. Un lote solo puede tener un RegistroCultivo activo a la vez
10. La etapa inicial de un RegistroCultivo es siempre la de orden 1
11. Una actividad solo puede registrarse si el lote tiene cultivo activo
12. Un agricultor solo puede ver y gestionar sus propias fincas y lotes
13. No se puede eliminar una finca con registros activos asociados
14. La calculadora de siembra es informativa, no modifica datos

---

## Stack técnico

```
Backend:      Java 25 + Spring Boot 4.0.6 + Spring Security 7
ORM:          Spring Data JPA + Hibernate 7
Base de datos: PostgreSQL 15 (Docker)
Frontend:     React + Vite (pendiente)
Contenedor:   Docker + Docker Compose
IDE:          IntelliJ IDEA Ultimate (licencia universitaria)
Repositorio:  GitHub — agro-plus-backend / agro-plus-frontend
```

### Docker
```yaml
# Rutina de trabajo
docker-compose up -d    # Levanta PostgreSQL antes de trabajar
docker-compose down     # Apaga al terminar — libera recursos
```

### application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agro_plus
spring.datasource.username=agro_admin
spring.datasource.password=agro1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
server.port=8081
```

---

## Estructura del proyecto

```
com.agro.agroplus/
├── AgroPlusApplication.java
├── config/           ← DataInitializer, configuración general
├── entity/           ← clases @Entity (tablas de BD)
├── repository/       ← interfaces JpaRepository
├── service/          ← lógica de negocio
├── controller/       ← endpoints REST
├── dto/              ← objetos de transferencia de datos
└── security/         ← JWT, filtros, SecurityConfig
```

---

## Roadmap de fases

```
Fase 0 — Modelado de dominio          ✅ COMPLETADA
Fase 1 — Setup técnico del proyecto   ✅ COMPLETADA
Fase 2 — MVP: Gestión de Lotes        🔄 EN CURSO
Fase 3+ — Bitácora, Fenología, IA     ⏳ Pendiente
```

---

## Fase 0 — Modelado de dominio ✅

**Entregables completados:**
- Entidades del dominio documentadas con fuentes académicas (papers por cultivo)
- Diagrama ER con relaciones correctas
- Reglas de negocio escritas
- Versión 1.1 aprobada

**Entidades del modelo:**

| Entidad | Tipo | Descripción |
|---|---|---|
| `Agricultor` | Transaccional | Usuario del sistema |
| `Finca` | Transaccional | Predio del agricultor |
| `Lote` | Transaccional | Parcela dentro de la finca |
| `RegistroCultivo` | Transaccional | Siembra activa en un lote ⭐ |
| `TipoCultivo` | Catálogo | Café, Cacao, Plátano, Maíz |
| `Variedad` | Catálogo | Variedades por cultivo con distancias |
| `EtapaFenologica` | Catálogo | Etapas ordenadas por cultivo |
| `Actividad` | Transaccional | Entrada de bitácora de campo |

---

## Fase 1 — Setup técnico ✅

**Completado:**
- [x] JDK 25 instalado
- [x] IntelliJ IDEA Ultimate con licencia universitaria
- [x] Docker Desktop con `agro_plus_db` corriendo en puerto 5432
- [x] DBeaver conectado a `localhost:5432/agro_plus`
- [x] Proyecto Spring Boot 4.0.6 creado con dependencias correctas
- [x] `application.properties` configurado
- [x] Spring Boot arranca y conecta a PostgreSQL exitosamente

**Fix importante de Spring Boot 4:**
> `DaoAuthenticationProvider` ya no acepta constructor vacío en Spring Security 7.  
> El `UserDetailsService` debe pasarse directo en el constructor:
> ```java
> new DaoAuthenticationProvider(agricultorService); // ✅ Boot 4
> ```

---

## Fase 2 — MVP: Gestión de Lotes 🔄

### Épica 1 — Autenticación y Seguridad ✅ COMPLETADA

| Tarea | Descripción | Estado |
|---|---|---|
| T-01 | Entidad `Agricultor` + tabla PostgreSQL | ✅ |
| T-02 | `AgricultorRepository` + `AgricultorService` | ✅ |
| T-03 | Endpoints `/api/auth/register` y `/api/auth/login` con JWT | ✅ |
| T-04 | `SecurityFilterChain` + `JwtFilter` | ✅ |

**Endpoints funcionando:**
```http
POST /api/auth/register   → { token, username }
POST /api/auth/login      → { token, username }
```

---

### Épica 2 — Catálogos del Sistema 🔄 EN CURSO

| Tarea | Descripción | Estado |
|---|---|---|
| T-05 | Entidades `TipoCultivo` + `Variedad` + `EtapaFenologica` | ✅ |
| T-06 | Seed de datos — 4 cultivos completos | 🔄 En progreso |
| T-07 | Endpoints GET de catálogos | ⏳ Pendiente |

**Estado actual de T-06:**
- `seedCacao()` — ✅ completado por el supervisor
- `seedCafe()` — ✅ completado por Andrés (revisado y aprobado con correcciones menores)
- `seedPlatano()` — ⏳ **pendiente — reto de Andrés**
- `seedMaiz()` — ⏳ **pendiente — reto de Andrés**

**Correcciones aplicadas en T-05:**
- `long` → `Long` en IDs (wrapper obligatorio para JPA)
- `double` → `Double`, `int` → `Integer` en campos numéricos
- `@Table(name="etapaFenologica")` → `etapa_fenologica` (snake_case)
- Removido `unique = true` en `nombre` de `EtapaFenologica` (etapas como "Cosecha" se repiten entre cultivos)

---

### Épica 3 — Gestión de Fincas y Lotes ⏳

| Tarea | Descripción | Estado |
|---|---|---|
| T-08 | Entidad `Finca` + relación con `Agricultor` | ⏳ |
| T-09 | CRUD de Finca — API REST | ⏳ |
| T-10 | Entidad `Lote` + relación con `Finca` | ⏳ |
| T-11 | CRUD de Lote — API REST | ⏳ |
| T-12 | Seguridad por propietario (agricultor ve solo sus datos) | ⏳ |

---

### Épica 4 — Registro de Cultivo ⏳

| Tarea | Descripción | Estado |
|---|---|---|
| T-13 | Entidad `RegistroCultivo` + relaciones | ⏳ |
| T-14 | Endpoint: Sembrar cultivo en un lote | ⏳ |
| T-15 | Endpoint: Avanzar etapa fenológica | ⏳ |

---

### Épica 5 — Calculadora de Siembra ⏳

| Tarea | Descripción | Estado |
|---|---|---|
| T-16 | Endpoint calculadora: plantas por área | ⏳ |

---

## Dinámica de trabajo

> Esta sección define cómo se trabaja en este proyecto con el supervisor (Claude).

**Regla principal:** El supervisor explica el concepto y construye el primer ejemplo. Andrés construye el siguiente de forma independiente y lo presenta para revisión antes de arrancar el proyecto.

**Flujo por tarea:**
1. Supervisor explica concepto con analogías y código comentado
2. Supervisor implementa el primer caso (ej: `seedCacao`)
3. Andrés implementa el caso paralelo (ej: `seedCafe`) de forma independiente
4. Andrés presenta el código — supervisor da retroalimentación con calificación
5. Si está aprobado → arrancar proyecto y verificar en DBeaver / Postman
6. Si hay errores → corregir antes de arrancar

**Estilo de retroalimentación:**
- Calificación numérica honesta con justificación
- Errores clasificados por severidad: crítico / funcional / de detalle
- Sin filtros — si está mal, se dice claro y se explica por qué

**Preguntas conceptuales siempre bienvenidas** — el "por qué" importa tanto como el "cómo".

---

## Próximo paso al retomar

**Estás aquí → T-06 Seed de datos**

Completar los métodos `seedPlatano()` y `seedMaiz()` en `DataInitializer.java` usando los datos de los documentos de Fase 0.

**Datos de referencia:**

### Plátano / Banano
```
Variedades:   Dominico Hartón, FHIA-20, Williams
              dist_plantas: 2.5–3.0m, dist_surcos: 3.0m, densidad: 1100–1600

Etapas (8):   Siembra colino → Macollamiento → Emisión → Parición
              → Llenado → Cosecha → Retoño
```

### Maíz
```
Variedades:   ICA V-109, Híbridos comerciales
              dist_plantas: 0.25–0.30m, dist_surcos: 0.70–0.90m, densidad: 40000–60000

Etapas (7):   Siembra → Emergencia → V1-V6 → V7-V12
              → R1 Floración → R3 Grano → R6 Madurez
```

Una vez completado el seed → verificar en DBeaver que las 4 tablas tienen datos → avanzar a **T-07 Endpoints GET de catálogos**.

---

*Documento generado como referencia de continuidad del proyecto Agro+*