import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { PostService } from '../post/post.service';
import { Post } from '../post/post';

@Component({
  selector: 'app-post-modal',
  template: `
    <form [formGroup]="formPost" (ngSubmit)="save()">
      <div class="modal-header">
        <h4 class="modal-title">O que você quer compartilhar?</h4>
        <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="modal-body">
        <div class="form-group">
          <label for="author">Autor</label>
          <input type="text" id="author" formControlName="author" class="form-control">
        </div>
        <div class="form-group">
          <textarea formControlName="text" rows="4" class="form-control"></textarea>
        </div> 
      </div>

      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" [disabled]="formPost.invalid">É isso!</button>
      </div>
    </form>
  `
})
export class PostModalComponent implements OnInit {
  
  formPost: FormGroup;

  constructor(private formBuilder: FormBuilder, 
              public activeModal: NgbActiveModal,
              private postService: PostService) { }

  ngOnInit() {    
    this.formPost = this.formBuilder.group({
      author: ['', [Validators.required, Validators.maxLength(20)]],
      text: ['', [Validators.required, Validators.maxLength(256)]]
    });
    
  }

  save() {
    let post = this.formPost.getRawValue() as Post;
    this.postService.insert(post).subscribe(
      res => {
        post = res;
        this.formPost.reset();
        this.activeModal.close(post);
      }
    )
  }
}