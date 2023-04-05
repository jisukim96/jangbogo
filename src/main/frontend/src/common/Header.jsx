import React, {Component} from 'react';
import { Link, withRouter} from 'react-router-dom';

import "../css/header.css"
import "../css/root.css"
import logoImg from "../img/logo.png"
import menuicon from "../img/menu-icon.png"

class Header extends Component {

  // 검색 관련 요청 시작

  constructor(props){
    super(props);
    this.state={
      query:'',
    };
    this.handleInputChange=this.handleInputChange.bind(this);
    this.handleKeyPress=this.handleKeyPress.bind(this);
  }

  componentDidMount(){
    const query=new URLSearchParams(window.location.search).get('query');
    if(query){
      this.setState({query});
    }
  }

  handleInputChange = (e) => {
    const query=e.target.value;
    console.log(query);
    this.setState({query},()=>{
      this.forceUpdate();
    });

  };
  
  handleKeyPress = (e) => {
    if (e.key === "Enter") {
      const { query } = this.state;

      const { onSearch } = this.props;  
      if (query && typeof onSearch === 'function') { 
        onSearch(query);  
      }
      this.props.history.push(`/search?query=${query}`);
    }
  
  };

  // 검색 관련 요청 끝

  categorymenu = [
    { id: 'fruit', value: '과일' },
    { id: 'vegetables', value: '채소' },
    { id: 'rice/grain', value: '쌀/잡곡' },
    { id: 'seafood', value: '수산물' },
    { id: 'animal products', value: '축산물' },
    { id: 'eggs/dairy', value: '계란/유제품' },
    { id: 'snack', value: '과자/간식' },
  ];

  render(){
  return (
    <header>
      <div className="header-container">
      <div className='header-top'>
        <div className='header-logo'>
            <Link to='/' onClick={() => {this.setState({query: ''}); 
                this.props.history.push('/');}}>
                <img src={logoImg} alt='로고' className='logo-img' />
            </Link>
        </div>
        <div className="search-form">
              <input
                type="text"
                placeholder="검색어를 입력해주세요" 
                value={this.state.query}
                name='query' id='query'
                onChange={this.handleInputChange} 
                onKeyPress={this.handleKeyPress}
              />
        </div>
            { this.props.authenticated ? (
                    <div className="header-top-menu-wrap">
                        <span className="header-top-menu" id="sign-up"><Link to='/signup'>찜한 상품</Link></span>
                        <span style={{marginLeft:20, marginRight:20}}>l</span>
                        <span className="header-top-menu" id="mypage"><Link to='/mypage'>마이페이지</Link></span>
                        <span style={{marginLeft:20, marginRight:20}}>l</span>
                        <span className="header-top-menu" id="logout" onClick={this.props.onLogout}>로그아웃</span>
                    </div>
                                ): (
                    <div className="header-top-menu-wrap">
                        <span className="header-top-menu" id="sign-up"><Link to='/signup'>회원가입</Link></span>
                        <span style={{marginLeft:20, marginRight:20}}>l</span>
                        <span className="header-top-menu" id="login"><Link to='/login'>로그인</Link></span>
                    </div>
            )}
        </div>
      </div>
      <div className='line'></div>
      <div className="header-menu">
        <div className="menu-wrap">
          <div className='category'>
          <img src={menuicon} alt="메뉴아이콘" style={{width:20}}/>
          <span style={{marginLeft:10}} className='category-title'>전체 카테고리
          </span>
          <div className='dropdown-menu'>
              {this.categorymenu.map((category) => (
                  <div key={category.id} >{category.value}</div> ))}
            </div>
          </div>
          <span style={{marginRight:30}}>커뮤니티</span>
          <span>문의 게시판</span>
        </div>
      </div>
    </header>
  );
  }
}
export default withRouter(Header);