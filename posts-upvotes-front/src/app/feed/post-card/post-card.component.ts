import { Component, Input } from '@angular/core';
import { PostService } from '../post/post.service';
import { Post } from '../post/post';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent {
  
  @Input() post: Post;
  faIcon = faThumbsUp;
  
  constructor(private postService: PostService) { }

  addUpvote() {
    this.postService.addUpvote(this.post.idPost).subscribe(
      res => this.post = res
    );
  }  
}