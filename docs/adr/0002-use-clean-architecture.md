# 2. use clean architecture

Date: 2020-07-02

## Status

Accepted

## Context

We need to build our application on a reliable architecture pattern.

## Decision

We choose to use the Clean Architecture as described in [Uncle Bob's blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## Consequences

By introducing this architecture pattern we will ease test implementation, ensure the respect of programming concept such as SRP, changing a component (such as the database) shall be easier.
