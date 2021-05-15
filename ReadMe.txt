Basic before and after Logging has been implemented using aspectJ and it has areas for improvement.
Ex before and after a login POST request:
2021-05-15T11:08:18.975196600 before: DtoLoginUser.setEmail(..) with args: [Ljava.lang.Object;@53c118f2
2021-05-15T11:08:18.977197100 after: DtoLoginUser.setEmail(..) with args: [Ljava.lang.Object;@7b40d597
2021-05-15T11:08:18.977197100 before: DtoLoginUser.setPassword(..) with args: [Ljava.lang.Object;@b571c81
2021-05-15T11:08:18.977197100 after: DtoLoginUser.setPassword(..) with args: [Ljava.lang.Object;@65247d7a
2021-05-15T11:08:19.000202400 before: UserController.login(..) with args: [Ljava.lang.Object;@52df736c
2021-05-15T11:08:19.000202400 before: DtoLoginUser.getPassword() with args: [Ljava.lang.Object;@65d51d45
2021-05-15T11:08:19.000202400 after: DtoLoginUser.getPassword() with args: [Ljava.lang.Object;@50474372
2021-05-15T11:08:19.000202400 before: UserController.hashPass(..) with args: [Ljava.lang.Object;@164df3d1
2021-05-15T11:08:19.026206800 after: UserController.hashPass(..) with args: [Ljava.lang.Object;@3f20a667
2021-05-15T11:08:19.026206800 before: DtoLoginUser.getEmail() with args: [Ljava.lang.Object;@72d2abf3
2021-05-15T11:08:19.026206800 after: DtoLoginUser.getEmail() with args: [Ljava.lang.Object;@18643a08
2021-05-15T11:08:19.026206800 before: UserService.login(..) with args: [Ljava.lang.Object;@61ce91ae
2021-05-15T11:08:19.714875900 before: UserDao.select(..) with args: [Ljava.lang.Object;@7d38dcfc
May 15, 2021 11:08:19 AM org.hibernate.internal.SessionImpl createCriteria
WARN: HHH90000022: Hibernate's legacy org.hibernate.Criteria API is deprecated; use the JPA javax.persistence.criteria.CriteriaQuery instead
2021-05-15T11:08:19.901918100 after: UserDao.select(..) with args: [Ljava.lang.Object;@6dbd0a17
2021-05-15T11:08:19.997939400 after: UserService.login(..) with args: [Ljava.lang.Object;@7d30837d
2021-05-15T11:08:19.997939400 before: User.getId() with args: [Ljava.lang.Object;@48017d33
2021-05-15T11:08:19.997939400 after: User.getId() with args: [Ljava.lang.Object;@22e53f70
2021-05-15T11:08:19.997939400 after: UserController.login(..) with args: [Ljava.lang.Object;@1e2223c8
2021-05-15T11:08:20.083958200 before: User.getId() with args: [Ljava.lang.Object;@2d2a05a3
2021-05-15T11:08:20.083958200 after: User.getId() with args: [Ljava.lang.Object;@5d80fb4b
2021-05-15T11:08:20.085957300 before: User.getFirstName() with args: [Ljava.lang.Object;@2d56b78d
2021-05-15T11:08:20.086958600 after: User.getFirstName() with args: [Ljava.lang.Object;@55990a7c
2021-05-15T11:08:20.086958600 before: User.getLastName() with args: [Ljava.lang.Object;@e582b7
2021-05-15T11:08:20.086958600 after: User.getLastName() with args: [Ljava.lang.Object;@84713ad
2021-05-15T11:08:20.086958600 before: User.getEmail() with args: [Ljava.lang.Object;@103527f7
2021-05-15T11:08:20.086958600 after: User.getEmail() with args: [Ljava.lang.Object;@de21567
2021-05-15T11:08:20.086958600 before: User.getPassword() with args: [Ljava.lang.Object;@213ece56
2021-05-15T11:08:20.086958600 after: User.getPassword() with args: [Ljava.lang.Object;@11d47ba7
2021-05-15T11:08:20.086958600 before: User.getAccountCreated() with args: [Ljava.lang.Object;@28e7fcf5
2021-05-15T11:08:20.086958600 after: User.getAccountCreated() with args: [Ljava.lang.Object;@35338da3
2021-05-15T11:08:20.089958300 before: User.getReceiveEmailReminders() with args: [Ljava.lang.Object;@6e7372e9
2021-05-15T11:08:20.089958300 after: User.getReceiveEmailReminders() with args: [Ljava.lang.Object;@49b54fdb
2021-05-15T11:08:20.089958300 before: User.getGoal() with args: [Ljava.lang.Object;@69536fe
2021-05-15T11:08:20.089958300 after: User.getGoal() with args: [Ljava.lang.Object;@64fa72da
