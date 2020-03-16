import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from './post';
import { environment } from '../../../environments/environment';

const api = environment.api;

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  findAll(page: number, size: number): Observable<Post[]> {
    const url = `${api}/posts`;

    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    
    return this.http.get<Post[]>(url, {params});
  }

  insert(post: Post): Observable<Post> {
    const url = `${api}/posts`;
    return this.http.post<Post>(url, post);
  }

  addUpvote(idPost: number): Observable<Post> {
    const url = `${api}/posts/${idPost}/upvote`;
    return this.http.put<Post>(url, '');
  }
}