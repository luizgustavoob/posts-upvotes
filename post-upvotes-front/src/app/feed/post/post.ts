export class Post {
  idPost: number;
  text: string;
  author: string;
  upvotes: number;
  dtPersist: Date = new Date();
}