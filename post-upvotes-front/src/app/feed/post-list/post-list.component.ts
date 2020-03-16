import { Component, Input } from '@angular/core';
import { Post } from '../post/post';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html'
})
export class PostListComponent { 

  @Input() posts: Post[] = [];

}