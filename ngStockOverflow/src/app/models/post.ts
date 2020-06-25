import { User } from './user';
import { Comment } from 'src/app/models/comment';

export class Post {
  id: number;
  title: string;
  description: string;
  createdAt: Date;
  enabled: boolean;
  user: User;
  comments: Comment[];

  constructor(
    id?: number,
    title?: string,
    description?: string,
    createdAt?: Date,
    enabled?: boolean
  ){
    this.id = id;
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.enabled = enabled;
  }
}
