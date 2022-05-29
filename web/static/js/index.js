makeElementTree = (params) => {
    // 将参数拿出来，不喜欢 params.xxx 的调用方式
    const { pid, list, pidFiled, labelFiled, valueFiled } = params
    // 构建一个内部函数，用于实现递归
    const makeTree = (pid, arr) => {
        const res = []
        arr.forEach(i => {
            if (i[pidFiled] === pid) {
                // 自己调用自己，递归查归属于自己的 children
                const children = makeTree(i[valueFiled], list)
                // 将原有的数据按照 element 的格式进行重构
                const obj = {
                    label: i[labelFiled],
                    value: i[valueFiled]
                }
                // 如果有 children 则插入 obj 中
                if (children.length) {
                    obj.children = children
                }
                res.push(obj)
            }
        })
        return res
    }
    return makeTree(pid, list)
}

handleTree = (data, id, parentId, children) => {
    let config = {
        id: id || 'id',
        parentId: parentId || 'parentId',
        childrenList: children || 'children'
    };

    var childrenListMap = {};
    var nodeIds = {};
    var tree = [];

    for (let d of data) {
        let parentId = d[config.parentId];
        if (childrenListMap[parentId] == null) {
            childrenListMap[parentId] = [];
        }
        nodeIds[d[config.id]] = d;
        childrenListMap[parentId].push(d);
    }

    for (let d of data) {
        let parentId = d[config.parentId];
        if (nodeIds[parentId] == null) {
            tree.push(d);
        }
    }

    for (let t of tree) {
        adaptToChildrenList(t);
    }

    function adaptToChildrenList(o) {
        if (childrenListMap[o[config.id]] !== null) {
            o[config.childrenList] = childrenListMap[o[config.id]];
        }
        if (o[config.childrenList]) {
            for (let c of o[config.childrenList]) {
                adaptToChildrenList(c);
            }
        }
    }
    return tree;
}