# Changelog — Swagger Petstore

Date: 20260520T165922
Mode: non-breaking

## Score Comparison

| | Before | After | Delta |
|---|---|---|---|
| Score | 68.54 | 71.35 | +2.81 |
| Level | ai-aware | ai-aware | |
| Grade | B+ | A- | +1 |

## Dimension Changes

| Dimension | Before | After | Delta |
|---|---|---|---|
| FC | 99.51 | 99.54 | +0.03 |
| DXJ | 68.42 | 78.74 | +10.32 |
| ARAX | 54.62 | 57.65 | +3.03 |
| AU | 93.70 | 93.70 | 0.00 |
| SEC | 42.50 | 42.50 | 0.00 |
| AID | 100.00 | 100.00 | 0.00 |

## Changes Applied

### SEC — Server URL
- Fixed relative server URL `/api/v3` to absolute HTTPS URL `https://petstore3.swagger.io/api/v3`

### ARAX — Description Coverage
- Added descriptions to all previously undescribed schema properties: `Order` (id, petId, quantity, shipDate, complete), `Category` (id, name), `User` (id, username, firstName, lastName, email, password, phone, userStatus), `Tag` (id, name), `Pet` (id, name, photoUrls, tags), `ApiResponse` (code, type, message)
- Added description to `api_key` parameter on `DELETE /pet/{petId}`

### DXJ — Example Density
- Added `example` field to `Order.shipDate` and `Order.complete` schema properties
- Added `example` fields to `Tag.id` and `Tag.name` schema properties
- Added `example` fields to `ApiResponse.code`, `ApiResponse.type`, `ApiResponse.message` schema properties
- Added `example` to `Pet.photoUrls` schema property
- Added request body examples (JSON) to: `PUT /pet`, `POST /store/order`, `POST /user/createWithList`, `PUT /user/{username}`
- Added response body examples (JSON) to: `POST /pet`, `PUT /pet`, `GET /pet/findByTags`, `POST /store/order`, `GET /store/order/{orderId}`, `POST /user`, `POST /user/createWithList`, `GET /user/{username}`, `GET /user/login`
- Added response body examples (JSON) to: `POST /pet/{petId}`, `POST /pet/{petId}/uploadImage`, `GET /store/inventory`
- Added request and response body examples (XML) to multiple operations

### DXJ / SEC — Security Documentation
- Added `security: []` to 10 intentionally public operations (user registration, login, logout, user CRUD, store order operations) to explicitly document their public access intent and resolve `security-defined` validation warnings

## Output Files

- `openapi.json` — improved specification
- `meta/qa/20260520T165922/overlay.json` — overlay
- `meta/qa/20260520T165922/changelog.md` — this file
