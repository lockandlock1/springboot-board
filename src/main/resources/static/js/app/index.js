var tagPostlist;

var index = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-follow').on('click', function () {
            _this.follow();
        })

        $('#btn-follow-cancel').on('click', function () {
            _this.followCancel();
        })

        $('#btn-search').on('click', function () {
            _this.search();
        })
    },
    save : function () {
      var data = {
          title: $('#title').val(),
          author: $('#author').val(),
          content: $('#content').val()
      };

      $.ajax({
         type: 'POST',
         url: '/api/v1/posts',
         dataType: 'json',
         contentType: 'application/json; charset=utf-8',
         data: JSON.stringify(data)
      }).done(function () {
          alert('글이 등록되었습니다.');
          window.location.href = '/';
      }).fail(function (error) {
          alert(JSON.stringify(error));
      });
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href ='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href ='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    follow : function () {
        var email = $('#email').val();

        var data = {
            toEmail: email
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/following',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('팔로우 요청 되었습니다.');
            window.location.href = '/user/info/' + email
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    
    followCancel : function () {
        var email = $('#email').val();

        var data = {
            toEmail: email
        };

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/follow/cancel',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('팔로우가 취소 되었습니다.');
            window.location.href ='/user/info/' + email;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    search : function () {
        var tagWord = $('#search').val();

        $.ajax({
            type: 'GET',
            url: '/search?tag=' + tagWord
        }).done(function () {
            window.location.href = '/search/tag?word=' + tagWord;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }

};

index.init();