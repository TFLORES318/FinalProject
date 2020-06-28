import { Comment } from 'src/app/models/comment';
import { CommentRatingId } from './comment-rating-id';
import { User } from './user';

export class CommentRating {
  id: CommentRatingId;
  rating: number;
  note: string;
  createdAt: Date;
  user: User;
  comment: Comment;

  constructor(
    id?: CommentRatingId,
    rating?: number,
    note?: string,
    createdAt?: Date,
    user?: User,
    comment?: Comment
  ){
    this.id = id;
    this.rating = rating;
    this.note = note;
    this.createdAt = createdAt;
    this.user=user;
    this.comment=comment;
  }
}
