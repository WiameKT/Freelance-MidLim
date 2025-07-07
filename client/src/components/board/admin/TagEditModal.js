import axios from "axios";
import {renderModal} from "components/commons/tag-modal-commons";
import {BoardContext} from "context";
import {useContext, useEffect, useRef, useState} from "react";
import {UiThemeContext} from "ui";
import {popupError, popupNotification, popupWarning} from "utils/basic-utils";

const TagEditModal = ({tag, isOpen, onHide, onEdit}) => {
    const {getTheme} = useContext(UiThemeContext);
    const {data} = useContext(BoardContext);
    const [color, setColor] = useState(tag.color);
    const [tagData, setTagData] = useState({name: "", roadmapIgnored: false, publicUse: false});
    const ref = useRef();
    useEffect(() => {
        setColor(tag.color);
        if(tag.roadmapIgnored != null && tag.publicUse != null) {
            setTagData({id: tag.id, name: tag.name, roadmapIgnored: tag.roadmapIgnored, publicUse: tag.publicUse});
        }
    }, [tag]);

    const onClick = () => {
        const name = tagData.name;
        if (name.length < 3 || name.length > 20) {
            popupWarning("Tag name must be between 3 and 20 characters");
            return Promise.resolve();
        }
        const roadmapIgnored = tagData.roadmapIgnored;
        const publicUse = tagData.publicUse;
        return axios.patch("/boards/" + data.discriminator + "/tags/" + tag.id, {
            name, color, roadmapIgnored, publicUse,
        }).then(res => {
            if (res.status !== 200 && res.status !== 201) {
                popupError();
                return;
            }
            onHide();
            onEdit(tag, res.data);
            popupNotification("Tag edited", getTheme());
        });
    };
    return renderModal(isOpen, onHide, "Edit Tag", onClick, color, setColor, tagData, setTagData, ref);
};

export default TagEditModal;