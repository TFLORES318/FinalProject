export class CommentRatingId {
  commentId: number;
  userId: number;

  constructor(
    commentId?: number,
    userId?: number
  ){
    this.commentId = commentId;
    this.userId = userId;
  }
}
