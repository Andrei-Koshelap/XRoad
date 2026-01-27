# XRoad Cadastre Module
This repository contains the XRoad Cadastre Module, which provides an interface for accessing cadastre data through X-Road services.
## Overview
The XRoad Cadastre Module is designed to facilitate secure and efficient access to cadastre information.
It leverages the X-Road framework to ensure interoperability and security in data exchange.
## Components
- **Cadastre Service Interface**: Defines the operations and data structures for accessing cadastre data
- **Ruuter Orchestration**: Manages the flow of data and service calls within the X-Road ecosystem
- **Data Models**: Represents the cadastre data in a structured format
- **Security**: Implements authentication and authorization mechanisms to protect sensitive data

X-Road is not “a single server” but a trusted network where each participant manages its own node, all messages are signed, encrypted, and logged, and trust is established through a central configuration rather than direct agreements between services. That’s why containers are used.

1️⃣ xroad-central — 🧠 The central brain of the network
What it is:
Central Server (CS) - Does NOT participate in real requests
Not a proxy and not a router
What it’s for:
Stores:
- **list of members
- **subsystems
- **available services
- **certificates and keys

Generates the Global Configuration
Signs it with a signing key
Distributes it to all Security Servers
Key idea for the defense:

The Central Server is a single source of trust, but not a single point of data exchange.

2️⃣ xroad-ca — 🏛️ Certificate Authority (test)
What it is:
Test Certificate Authority
Used only in dev/test environments

3️⃣ xroad-ocsp — 🔍 Certificate revocation check
What it is:
OCSP responder
This is an online check (OCSP)

4️⃣ xroad-tsa — ⏱️ Time Stamping Authority
What it is:
Time-stamping server

5️⃣ xroad-ss-consumer — 🚪 Consumer Security Server
What it is:
Security Server (SS)

6️⃣ xroad-ss-provider — 🏠 Provider Security Server
What it is:
Security Server

How it all looks together (verbal diagram)
[Client App / Adapter]
        |
        v
[SS Consumer]  <-- access check, signing, encryption
        |
        v
[SS Provider]  <-- signature and timestamp verification
        |
        v
[REST / SOAP Business Service]


And constantly in the background:

CS → distributes configuration

CA → issues certificates

OCSP → checks certificates

TSA → applies timestamps


DSL in cadastre module describes the service interface and data contract, while Ruuter DSL describes orchestration logic.
These concerns are intentionally separated.