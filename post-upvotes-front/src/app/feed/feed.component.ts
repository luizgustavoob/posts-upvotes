import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostModalComponent } from './post-modal/post-modal.component';
import { PostService } from './post/post.service';
import { Post } from './post/post';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html'
})
export class FeedComponent implements OnInit {
  
  posts: Post[] = [];
  page: number = 0;
  size: number = 5;
  hasMore: boolean = true;
  
  constructor(private postService: PostService,
              private modalService: NgbModal) { }
  
  ngOnInit() {
    this.postService.findAll(this.page, this.size).subscribe(res => this.posts = res);
  }
  
  loadMore() {
    this.postService.findAll(++this.page, this.size).subscribe(
      res => {
        this.posts = this.posts.concat(res);
        if(!res.length) {
          this.hasMore = false;
        }
      }
    ) 
  }

  addPost() {
    const modalRef = this.modalService.open(PostModalComponent);    
    modalRef.result.then(
      result => {
        this.postService.findAll(0, this.size).subscribe(res => this.posts = res);
        this.hasMore = true;
      }, 
      reason => console.log('Fechou sem postar', reason));
  }
}