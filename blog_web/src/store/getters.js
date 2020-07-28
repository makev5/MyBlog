const getters = {
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  blogList: state => state.blog.blogList,
  total: state => state.blog.total,
  currentPage: state => state.blog.listQuery.currentPage,
  pageSize: state => state.blog.listQuery.pageSize,
  pages: state => state.blog.pages
}
export default getters
