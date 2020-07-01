## StockOverflow

### Final Capstone Project for 16-week Skill Distillery Bootcamp

| Returns | Verb | URI | Description |
|---------|------|-----|-------------|
| User | POST | /register | Register user |
| User | GET | /authenticate | Returns user logged in |
| `List<Comment>` | GET | api/posts/{postId}/comments | Retrieves all comments for a specific post |
| Comment | POST | api/posts/{postId}/comments | Creates comment on a post |
| Comment | PUT | api/posts/{postId}/comments/{commentId}| Update an existing comment |
| Void | PUT | api/posts/{postId}/delete/{commentId} | Disables a comment |
| `List<CommentRating>` | GET | api/comments/ratings/user | Get comment ratings for logged in user |
| `List<CommentRating>` | GET | api/comments/ratings/{userId} | Get comment ratings for a user |
| CommentRating | POST | api/comments/ratings | Create a comment rating |
| `List<Post>` | GET | api/posts | Get all posts |
| `List<Post>` | GET | api/posts/search/{title} | Get all posts by title |
| `List<Post>` | GET | api/users/posts | Get all posts by logged in user |
| Post | GET | api/posts/{postId} | Get post by ID |
| Post | POST | api/posts | Create a new post |
| Post | PUT | api/posts/{postId} | Update a post |
| Void | PUT | api/posts/disable/{postId} | Disable a post |
| `List<Stock>` | GET | api/stocks | Get a list of stock |
| Stock | GET | api/stocks/{stockSymbol} | Get a stock by its symbol/ID |
| Stock | POST | api/stocks | Create a new stock |
| Stock | PUT | api/stocks/{stockSymbol} | Update a stock |
| Void | DELETE | api/stocks/{stockSymbol} | Delete a stock |
| `List<Webinar>` | GET | api/webinars | Retrieve a list of webinars |
| Webinar | GET | api/webinars/{webinarId} | Retrieve webinar by ID |
| Webinar | POST | api/webinars | Create a webinar |
| Webinar | PUT | api/webinars/{webinarId} | Update a webinar |
| Void | PUT | api/webinars/delete/{webinarId} | Disable a webinar |
| `List<User>` | PUT | api/webinars/{webinarId}/signUp | Add user to attendees for a webinar |
| `List<User>` | PUT | api/webinars/{webinarId}/withdraw | Remove user to attendees for a webinar |
| `List<Stock>` | GET | api/users/stocks | Retrieve user's list of stocks |
| `List<Webinar>` | GET | api/users/webinars | Retrieve logged in user's list of webinars attending |
| `List<Webinar>` | GET | api/users/{userId}/webinars | Retrieve user's list of webinars attending |
| `List<Webinar>` | GET | api/users/{userId}/webinarshostedbyuser | Retrieve user's list of webinars that user is hosting |
| `List<Webinar>` | GET | api/users/webinarshosting | Retrieve logged in user's list of webinars that user is hosting |
| `List<Post>` | GET | api/users/{userId}/posts | Retrieve user's list of posts that user made |
| `List<User>` | GET | api/webinar/{webinarId}/users | Retrieve users attending webinar |
| User | GET | api/users/{userId} | Retrieve a user by ID |
| User | GET | api/users/userpro | Retrieve logged in user |
| User | PUT | api/users/update | Update logged in user |
| User | PUT | api/users/admin/update | Admin page to update any user |
| Void | PUT | api/users/delete | Disable logged in user |
| `List<User>` | GET | api/users | Retrieve list of users |

#### Team Peppermint Paradise

* Tabatha Flores - Developer, Repo Owner
* Toni Papp - Developer, DBA
* Rich Wasek - Developer, Scrum Master

### Overview

Credentials:
| Username | Password |
| admin | admin |
| rwasek | admin |

StockOverflow is a single-page application that caters to people who have little to no knowledge of stocks and are interested in learning more. It also caters to more experienced users who want to share their knowledge. StockOverflow offers resources and advice targeted at those fresh to the stock market.

On the home page, a user can view our mission statement, carousel links to other parts of the application, helpful stock widgets, and testimonials.

![Image of Home page](https://i.imgur.com/iWEwvJe.png)

In the forum, a user is able to perform all CRUD methods on posts and comments they have made. They are also able to comment on other posts and rate the comments of other users. The ratings affect the reputation and "flair" of the user in question. 

On the webinar page, a user can sign up or withdraw from a webinar and view the attendees. Verified users are able to perform all CRUD methods on a webinar.

On the profile page, the user can view their posts, webinars attendings, and the webinars they're hosting if applicable. They can manage their account settings by updating their personal information, applying for verification, or deleting their account. From the profile page, they can view their portfolio. An admin can view the admin page from their profile.

![Image of Portfolio](https://i.imgur.com/zf6dBuN.png)

On the portfolio page, a user can perform all CRUD methods on their list of stocks. A user is able to view resources for a specific stock. A user is also able to use stock tool widgets on their portfolio.

The navbar also has a search bar which searches through forum posts.

An admin can approve other users for verification and disable other users.

The resources page is a collection of FAQs, articles, and videos tailored for beginners.

### Implementation

This project had a total of eleven tables, eight were used. The table used were comment, comment_rating, post, stock, user, user_stock, user_webinar, and webinar. There were two virtual join tables and a third join table that was also an entity, (comment_rating). The user_webinar table is to track the list of webinars the user is attending and stands in contrast to the user_id in the webinar table, (this is the user that created the webinar).

![Image of DB Schema](https://i.imgur.com/0W8Necq.png)

### Technologies Used

* Java
* JUNIT
* Javascript
* TypeScript
* MySQL
* MySQL Workbench
* HTML 5
* CSS
* Bootstrap
* Spring Boot
* Spring RESTful Services
* Spring JPA/Hibernate
* Postman
* MAMP
* VS Code
* Angular
* Trello
* Figma
* Slack
* Zoom


### Lessons Learned

This project was a good lesson in being able to collaborate remotely. Our team had to work well via Slack, Zoom, and Trello in order to stay on the same page. Scrum meetings were also conducted over Zoom in order for us to move forward as a team.

This was also a good lesson in building a full stack application using Spring JPA, Spring Boot, Spring RESTful services, and Angular. As our final capstone project, we were able to revisit all methodologies from the ground up in order to produce our final product.

This sprint really helped us to cement our understanding of Angular, specfically service and component typescripts. After designing our database, we realized the amount of functionality we were hoping to implement was daunting. But our group was extreemly dedicated and communciated well. Thanks to this, our team was able to reach minimum viable product in four days. As such, we were able to implement three stretch goals to our final product.

### Stretch Goals

* Webinar ratings tied to user reputation
* Comments on webinars
* A user is able to make notes about the stocks in their portfolio
* Resources housed in database