# XRoad Cadastre Module

This repository contains the **XRoad Cadastre Module**, which provides an interface for accessing cadastre (land registry) data through **X-Road services**.

---

## Overview

The XRoad Cadastre Module is designed to facilitate **secure, standardized, and traceable** access to cadastral information.  
It leverages the **X-Road framework** to ensure interoperability, message integrity, encryption, and auditability across organizational boundaries.

The module follows **Bükstack DSL architectural principles**, clearly separating:
- service contracts
- orchestration logic
- transport and security concerns

---

## Components

- **Cadastre Service Interface**  
  Defines operations and data structures for accessing cadastral data.

- **Ruuter Orchestration**  
  Manages request flow, routing, and policies within the X-Road ecosystem using DSL.

- **Data Models**  
  Structured representations of cadastral data.

- **Security**  
  Authentication, authorization, message signing, and encryption based on X-Road standards.

---

## X-Road Architecture Context

X-Road is **not a single server**, but a **trusted distributed network** where:
- each participant operates its own node
- all messages are **signed, encrypted, and logged**
- trust is established via **central configuration**, not point-to-point agreements

This is why the prototype uses **containerized X-Road components**.

---

## X-Road Infrastructure Components

### 1. xroad-central — Central Server (CS)

**What it is:**
- Central Server (CS)

**Responsibilities:**
- Maintains:
        - list of members
        - subsystems
        - available services
        - certificates and keys
- Generates **Global Configuration**
- Signs configuration and distributes it to all Security Servers

**Key principle:**

> The Central Server is a single source of trust, but not a single point of data exchange.

---

### 2. xroad-ca — Certificate Authority (Test)

- Test Certificate Authority
---

### 3. xroad-ocsp — Certificate Revocation Check

- OCSP responder
---

### 4. xroad-tsa — Time Stamping Authority

- Provides trusted timestamps for signed messages
---

### 5. xroad-ss-consumer — Consumer Security Server

- X-Road Security Server on the **consumer** side
- Performs:
        - access control
        - message signing
        - encryption (mTLS)
---

### 6. xroad-ss-provider — Provider Security Server

- X-Road Security Server on the **provider** side
- Performs:
        - signature validation
        - timestamp verification
        - forwarding to business service
---

## Prototype Execution Flow (Cadastre via X-Road)

The following diagram illustrates how the **XRoad Cadastre Module prototype** operates end-to-end.

```text

+--------------------------+
|        Ruuter             |
|  Orchestration Layer      |
|  - DSL-based routing      |
|  - Policy checks          |
|  - Correlation ID         |
+-------------+------------+
              |
              | Internal REST call
              v
+--------------------------+
|  XRoad Cadastre Adapter  |
|  - DSL service interface |
|  - Request mapping       |
|  - Response normalization|
+-------------+------------+
              |
              | X-Road REST request
              v
+--------------------------+
|  X-Road Security Server  |
|       (Consumer)         |
|  - Access control        |
|  - Message signing       |
|  - Encryption (mTLS)     |
+-------------+------------+
              |
              | Signed & encrypted message
              v
+--------------------------+
|  X-Road Security Server  |
|       (Provider)         |
|  - Signature validation  |
|  - Timestamp verification|
+-------------+------------+
              |
              | Local REST / SOAP call
              v
+--------------------------+
|   Cadastre Information   |
|        System            |
|  - Business logic        |
|  - Data source           |
+--------------------------+

```

## Responsibilities

### Central Server (CS)
- Distributes **signed global configuration** to all X-Road Security Servers.

### Certificate Authority (CA)
- Issues certificates used by X-Road members and Security Servers.

### OCSP
- Validates certificate revocation status.

### Time Stamping Authority (TSA)
- Provides trusted timestamps for signed messages.

---

## Step-by-step Flow Description

1. The **user** initiates a request via **Bürokratt UI**.
2. **TIM** authenticates the user and establishes a session.
3. **Ruuter**:
   - validates the JWT
   - applies orchestration rules defined in **Ruuter DSL**
   - assigns correlation identifiers for traceability
4. **XRoad Cadastre Adapter**:
   - implements the cadastre service contract defined in DSL
   - transforms the request into an **X-Road–compatible REST call**
5. **X-Road Security Server (Consumer)**:
   - checks access rights
   - signs the message
   - encrypts communication using **mTLS**
6. **X-Road Security Server (Provider)**:
   - verifies the signature and timestamp
   - forwards the request to the cadastre service
7. **Cadastre Information System** processes the request and returns the response.
8. The adapter normalizes the response into a **unified response format** before returning it to Bürokratt.

---

## Relation to DSL Architecture

### Cadastre Module DSL
- Defines service interfaces
- Specifies input/output data contracts
- Is **transport-agnostic** (REST / SOAP)

### Ruuter DSL
- Defines orchestration logic
- Controls routing and policies
- Contains **no business or X-Road–specific logic**

### This separation ensures:
- clear responsibilities
- easier testing
- protocol independence
- compliance with **Bükstack architectural principles**

---

## Key Architectural Principles

- Full compliance with **X-Road standards**
- Secure, signed, and encrypted message exchange
- Clear traceability (**from whom → to whom**)
- Separation of orchestration and integration concerns
- Reproducible, containerized prototype