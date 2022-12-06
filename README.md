# Specification SDK for Java

This repository contains interfaces and classes to implement business rules, aka Specifications, in Java.

The **specification pattern** is a design pattern in Domain-Driven Design whereby business rules can be recombined by chaining the business rules together using boolean logic (from [Wikipedia](https://en.wikipedia.org/wiki/Specification_pattern)). The central object `Specification` implements the business rule and offers methods to test whether or not a certain value satisfies the rule.

## Installation

Add this dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>de.robertwitt</groupId>
  <artifactId>ddd-specification</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Example

The following is a reduced example from the [Wikipedia article](https://en.wikipedia.org/wiki/Specification_pattern).

Invoices where the due date is older than 30 days and the customer has already been noticed, should be sent to a collection agency for collection. Two business rules can be extracted from this requirement:

1. Invoices overdue for 30 days
2. Customer has been noticed already at least once

To code a business rule, the `Specification` interface must be implemented. The central method `isSatisfiedBy` compares a given value against the business rule logic. The abstract class `AbstractSpecification` provides a default implementation already so it can be used as base class.

For example, this is the (dummy) implementation of the first business rule, _Invoices overdue for 30 days_:

```java
class OverDueSpecification extends AbstractSpecification<Invoice> {
  @Override
  boolean isSatisfiedBy(Invoice candidate) {
    return ChronoUnit.DAYS.between(LocalDate.now(), candidate.dueDate()) > 30;
  }
}
```

Clients can use the specification and chain them by logical AND:

```java
var overDue = new OverDueSpecification();
var noticeSent = new NoticeSentSpecification();
var toBeCollected = overDue.and(noticeSent);

if (toBeCollected.isSatisfiedBy(anInvoice)) {
  // Send invoice to collection agency
}
```
