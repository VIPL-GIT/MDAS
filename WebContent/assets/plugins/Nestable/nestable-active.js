$(document).ready(function () {

   

    // activate Nestable for list 1
    $('#nestable').nestable({
        group: 1
    });

    

    // output initial serialised data
   // updateOutput($('#nestable').data('output', $('#nestable-output')));
    //updateOutput($('#nestable2').data('output', $('#nestable2-output')));

    $('#nestable-menu').on('click', function (e)
    {
        var target = $(e.target),
                action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });

   

});